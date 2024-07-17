package top.xiaolinz.wechat.bot.extend.gpt;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.CallResponseSpec;
import top.xiaolinz.wechat.bot.core.CallbackListener;
import top.xiaolinz.wechat.bot.core.WechatHolder;
import top.xiaolinz.wechat.bot.core.model.callback.ReceiveMessageCallback;

/**
 * 抽象 GPT 消息回调
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/14
 */
public abstract class AbstractGPTMessageCallback implements CallbackListener<ReceiveMessageCallback> {

    private final Cache<String, ChatContext> chatContextCache = Caffeine.newBuilder()
                                                                        .expireAfterAccess(20, TimeUnit.SECONDS)
                                                                        .build();
    private final ChatClient                 chatClient;

    protected AbstractGPTMessageCallback(ChatClient.Builder chatClientBuilder) {
        chatClient = chatClientBuilder.build();
    }

    /**
     * 聊天
     *
     * @param roomId  房间id
     * @param wxid    来自 wxid
     * @param message 信息
     * @param msgId   消息 id
     * @author huangmuhong
     * @date 2024/07/15
     */
    protected void doChat(String roomId, String wxid, String message, String msgId) {

        if (StrUtil.isBlank(message)) {
            return;
        }

        // 获取聊天上下文
        final ChatContext context = chatContextCache.get(wxid, key -> createContext());

        context.addMessage(message);

        final CallResponseSpec spec = chatClient.prompt()
                                                .messages(context.getMessages())
                                                .call();

        // 添加到上下文
        final String content = spec.chatResponse()
                                   .getResult()
                                   .getOutput()
                                   .getContent();
        context.addMessage(content);

        // TODO 由于大模型默认返回的是 markdown 格式，这里需要转换为文本
        Parser parser = Parser.builder()
                              .build();
        Node document = parser.parse(content);
        TextContentRenderer renderer = TextContentRenderer.builder()
                                                          .build();
        final String text = renderer.render(document);

        final String sendWxid = roomId != null ? roomId : wxid;

        // 发送消息
        WechatHolder.getWechat()
                    .sendReferText(sendWxid, text, msgId);

    }

    /**
     * 创造上下文
     *
     * @return {@link ChatContext }
     * @author huangmuhong
     * @date 2024/07/14
     */
    private ChatContext createContext() {
        return new OpenAIChatContext();
    }

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

        return wxidList.size() == 1 && wxidList.contains(WechatHolder.getWxid());

    }
}
