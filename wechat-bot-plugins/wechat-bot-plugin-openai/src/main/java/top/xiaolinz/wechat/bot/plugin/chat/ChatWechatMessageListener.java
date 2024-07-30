package top.xiaolinz.wechat.bot.plugin.chat;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;
import org.dromara.hutool.core.text.CharPool;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import top.xiaolinz.wechat.bot.core.AbstractWechatMessageListener;
import top.xiaolinz.wechat.bot.core.WechatClient;
import top.xiaolinz.wechat.bot.core.WechatConfigHolder;
import top.xiaolinz.wechat.bot.core.enums.MessageTypeEnum;
import top.xiaolinz.wechat.bot.core.model.callback.ReceiveMessageCallback;
import top.xiaolinz.wechat.bot.core.model.callback.ReceiveMessageCallback.MessageData;
import top.xiaolinz.wechat.bot.plugin.chat.config.ChatMessageListenerProperties;
import top.xiaolinz.wechat.bot.plugin.chat.config.ChatMessageListenerProperties.GroupChatConfig;

/**
 * 聊天微信消息监听
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/14
 * @see AbstractWechatMessageListener
 */
@Service
public class ChatWechatMessageListener
    extends AbstractWechatMessageListener<ChatMessageListenerProperties, ReceiveMessageCallback> {

    private static final String                    AT_REGEX     = "@[^\\s]+\\s*";
    private final        Cache<String, ChatMemory> cacheMemorys = Caffeine.newBuilder()
                                                                          .expireAfterAccess(300, TimeUnit.SECONDS)
                                                                          .build();

    /**
     * 是否@我
     *
     * @param messageData 消息数据
     * @return boolean
     * @author huangmuhong
     * @date 2024/07/14
     */
    protected boolean isAtMe(ReceiveMessageCallback.MessageData messageData) {
        final List<String> wxidList = messageData.getAtWxidList();
        if (wxidList.isEmpty()) {
            return false;
        }
        return wxidList.size() == 1 && wxidList.contains(WechatConfigHolder.getBindWxid());

    }

    /**
     * 移除@信息
     *
     * @param msg 信息
     * @return {@link String }
     * @author huangmuhong
     * @date 2024/07/15
     */
    private String removeAt(String msg) {
        // @格式为：@昵称+空格,使用正则匹配
        // 例如：@小明 你好
        // 使用 Pattern.compile() 方法编译正则表达式
        Pattern compiledPattern = Pattern.compile(AT_REGEX);

        // 使用 Matcher 对象进行匹配和替换
        Matcher matcher = compiledPattern.matcher(msg);
        return matcher.replaceAll("");
    }

    @Override
    public void listener(ReceiveMessageCallback data, WechatClient wechatClient) {
        final MessageData messageData = data.getData();
        final String      roomId      = messageData.getFromWxid();
        final ChatClient  chatClient  = createChatClient(getConfig(), roomId);

        // 去除文本中的@信息
        final String msg = removeAt(messageData.getMsg());

        // 判断是否群聊没有客户端或者没有消息
        if (chatClient == null || StrUtil.isBlank(msg) || !isAtMe(messageData)) {
            return;
        }

        // 构建缓存 key
        final String cacheKey = roomId + CharPool.AT + messageData.getFinalFromWxid();

        final ChatMemory chatMemory = cacheMemorys.get(cacheKey, key -> new InMemoryChatMemory());

        // 发送请求
        final Flux<String> flux = chatClient.prompt()
                                            .advisors(new SimpleLoggerAdvisor(),
                                                      new MessageChatMemoryAdvisor(chatMemory))
                                            .user(msg)
                                            .stream()
                                            .content();
        // 阻塞获取完整结果
        String content = flux.collectList()
                             .block()
                             .stream()
                             .collect(Collectors.joining());

        // 解析 markdown
        Parser parser = Parser.builder()
                              .build();
        // 去除 markdown 元数据等特殊字符块
        Node document = parser.parse(content);
        TextContentRenderer renderer = TextContentRenderer.builder()
                                                          .build();
        final String text = renderer.render(document);

        // 发送消息
        wechatClient.sendReferText(roomId, text, messageData.getMsgId());
    }

    /**
     * 创建聊天客户端
     *
     * @param config 配置
     * @param roomId 房间id
     * @return {@link ChatClient }
     * @author huangmuhong
     * @date 2024/07/30
     */
    private ChatClient createChatClient(ChatMessageListenerProperties config, String roomId) {
        final GroupChatConfig groupChatConfig = config.getGroupChatConfig()
                                                      .stream()
                                                      .filter(group -> group.getRoomId()
                                                                            .equals(roomId))
                                                      .findFirst()
                                                      .orElse(null);
        if (groupChatConfig == null) {
            return null;
        }
        final OpenAiApi       openAiApi = new OpenAiApi(groupChatConfig.getBaseUrl(), groupChatConfig.getApiKey());
        final OpenAiChatModel chatModel = new OpenAiChatModel(openAiApi, groupChatConfig.getOptions());
        return ChatClient.builder(chatModel)
                         .defaultSystem(new SystemPromptTemplate(groupChatConfig.getSystemPrompt()).getTemplate())
                         .build();
    }

    @Override
    public boolean support(MessageTypeEnum type) {
        return type.equals(MessageTypeEnum.GROUP_MESSAGE);
    }
}
