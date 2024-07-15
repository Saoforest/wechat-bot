package top.xiaolinz.wechat.bot.extend.gpt;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.CallResponseSpec;
import top.xiaolinz.wechat.bot.core.CallbackListener;
import top.xiaolinz.wechat.bot.core.Wechat;
import top.xiaolinz.wechat.bot.core.WxidHolder;
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
    private final Wechat                     wechat;

    protected AbstractGPTMessageCallback(ChatClient.Builder chatClientBuilder, Wechat wechat) {
        chatClient  = chatClientBuilder.build();
        this.wechat = wechat;
    }

    /**
     * 聊天
     *
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

        if (StrUtil.isNotBlank(roomId)) {
            // 发送消息
            wechat.sendReferText(roomId, content, msgId);
        } else {
            // 发送消息
            wechat.sendReferText(wxid, content, msgId);
        }
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

        return wxidList.size() == 1 && wxidList.contains(WxidHolder.get());

    }
}
