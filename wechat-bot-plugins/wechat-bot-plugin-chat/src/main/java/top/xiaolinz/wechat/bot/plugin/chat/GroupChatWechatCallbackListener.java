package top.xiaolinz.wechat.bot.plugin.chat;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.core.text.UnicodeUtil;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import reactor.core.publisher.Flux;
import top.xiaolinz.wechat.bot.core.WechatBotManager;
import top.xiaolinz.wechat.bot.core.WechatCallbackListener;
import top.xiaolinz.wechat.bot.core.enums.WechatMessageTypeEnum;
import top.xiaolinz.wechat.bot.core.model.callback.ReceiveMessageWechatCallback;
import top.xiaolinz.wechat.bot.core.model.callback.ReceiveMessageWechatCallback.MessageData;
import top.xiaolinz.wechat.bot.plugin.chat.config.WechatChatPluginProperties;
import top.xiaolinz.wechat.bot.plugin.chat.config.WechatChatPluginProperties.GroupMappingConfig;

/**
 * 群聊天微信回调监听
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/14
 * @see WechatCallbackListener
 */
public class GroupChatWechatCallbackListener implements WechatCallbackListener<ReceiveMessageWechatCallback> {

    private static final String                  AT_REGEX = "@.+\\p{Zs}[1]";
    private static final String                    BLACK_LIST_MESSAGE = "检测到敏感词，已自动过滤！非法词：";
    private final        Map<String, ChatClient> chatClientMap;
    private final        Cache<String, ChatMemory> cacheMemorys       = Caffeine.newBuilder()
                                                                                .expireAfterAccess(300,
                                                                                                   TimeUnit.SECONDS)
                                                                                .build();
    private final WechatChatPluginProperties config;

    public GroupChatWechatCallbackListener(Map<String, ChatClient> chatClientMap, WechatChatPluginProperties config) {
        this.chatClientMap = chatClientMap;
        this.config = config;
    }

    /**
     * 是否@我
     *
     * @param messageData 消息数据
     * @return boolean
     * @author huangmuhong
     * @date 2024/07/14
     */
    protected boolean isAtMe(ReceiveMessageWechatCallback.MessageData messageData) {
        final List<String> wxidList = messageData.getAtWxidList();
        if (wxidList.isEmpty()) {
            return false;
        }
        return wxidList.size() == 1 && wxidList.contains(WechatBotManager.getWeChatBotConfig()
                                                                         .getWxid());

    }

    /**
     * 移除@我
     *
     * @param msg 信息
     * @return {@link String }
     * @author huangmuhong
     * @date 2024/07/15
     */
    private String removeAtMe(String msg) {
        // @格式为：@昵称+空格,使用正则匹配
        // 例如：@小明 你好
        // 使用 Pattern.compile() 方法编译正则表达式
        Pattern compiledPattern = Pattern.compile(AT_REGEX);

        // 使用 Matcher 对象进行匹配和替换
        Matcher matcher = compiledPattern.matcher(msg);
        if (matcher.find()) {
            return matcher.replaceFirst("");
        }
        return msg;
    }

    @Override
    public void listener(ReceiveMessageWechatCallback data) {
        final MessageData messageData = data.getData();

        if (!isAtMe(messageData)) {
            return;
        }

        final String groupId = messageData.getFromWxid();
        final GroupMappingConfig mappingConfig = config.getGroupMappingConfig()
                                                            .stream()
                                                            .filter(config -> config.getRoomId()
                                                                                    .equals(groupId))
                                                            .findFirst()
                                                            .orElse(null);
        if (mappingConfig == null) {
            return;
        }

        final ChatClient chatClient = chatClientMap.get(mappingConfig.getChatClientName())
                                                   .mutate()
                                                   .defaultSystem(mappingConfig.getPrompt())
                                                   .defaultAdvisors(
                                                       advisorSpec -> advisorSpec.param(CHAT_MEMORY_RETRIEVE_SIZE_KEY,
                                                                                        mappingConfig.getMaxContextLength()))
                                                   .build();

        // 群聊没有配置 chatClient 退出
        if (chatClient == null) {
            return;
        }

        // 转换为字符串
        final String msg = UnicodeUtil.toString(messageData.getMsg());
        
        // 消息为空退出
        if (StrUtil.isBlank(msg)) {
            return;
        }

        // 敏感词处理
        final String msgId = messageData.getMsgId();
        if (SensitiveWordHelper.contains(msg)) {
            final List<String> sensitiveWords = SensitiveWordHelper.findAll(msg);
            WechatBotManager.getWechatClient()
                            .sendReferText(groupId, BLACK_LIST_MESSAGE + StrUtil.join(",", sensitiveWords), msgId);
            return;
        }

        final ChatMemory chatMemory = cacheMemorys.get(groupId, key -> new InMemoryChatMemory());

        // 发送请求
        final Flux<String> contentFlux = chatClient.prompt()
                                                   .advisors(new SimpleLoggerAdvisor(),
                                                             new PromptChatMemoryAdvisor(chatMemory))
                                                   .user(msg)
                                                   .stream()
                                                   .content();
        // 阻塞获取完整结果
        String content = contentFlux.collectList()
                                    .block()
                                    .stream()
                                    .collect(Collectors.joining());

        // 解析 markdown
        Parser parser = Parser.builder()
                              .build();
        Node document = parser.parse(content);
        TextContentRenderer renderer = TextContentRenderer.builder()
                                                          .build();
        final String text = renderer.render(document);

        // 发送消息，并替换敏感词
        WechatBotManager.getWechatClient()
                        .sendReferText(groupId, SensitiveWordHelper.replace(text), msgId);
    }

    @Override
    public boolean support(WechatMessageTypeEnum type) {
        return type.equals(WechatMessageTypeEnum.GROUP_MESSAGE);
    }
}
