package top.xiaolinz.wechat.bot.core;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.beans.factory.ObjectProvider;
import top.xiaolinz.wechat.bot.core.model.WechatCallBackRequest;

/**
 * 回调微信信息处理程序
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/28
 * @see WechatRequestHandler
 */
public class CallbackWechatRequestHandler implements WechatRequestHandler<WechatCallBackRequest> {
    private final List<WechatCallbackListener<?>> listeners;
    private final ExecutorService                 executor = Executors.newVirtualThreadPerTaskExecutor();

    public CallbackWechatRequestHandler(ObjectProvider<WechatCallbackListener<?>> listeners) {
        this.listeners    = listeners.orderedStream()
                                     .toList();
    }

    @Override
    public void handle(WechatCallBackRequest message) {
        // 如果消息的发送者不是当前绑定的微信账号，则不处理
        if (!StrUtil.equals(message.getWxid(), WechatManager.getWeChatBotConfig()
                                                            .getWxid())) {
            return;
        }
        // 异步处理消息
        listeners.stream()
                 .filter(listener -> listener.support(message.getEvent()))
                 .forEach(listener -> executor.execute(() -> listener.listener(message.getData())));
    }
}
