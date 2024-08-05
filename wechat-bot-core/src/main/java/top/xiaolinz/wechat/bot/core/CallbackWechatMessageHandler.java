package top.xiaolinz.wechat.bot.core;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.beans.factory.ObjectProvider;
import top.xiaolinz.wechat.bot.core.message.AbstractWechatMessageListener;
import top.xiaolinz.wechat.bot.core.model.WechatCallBackMessage;

/**
 * 回调微信信息处理程序
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/28
 * @see WechatMessageHandler
 */
public class CallbackWechatMessageHandler implements WechatMessageHandler<WechatCallBackMessage> {
    private final List<AbstractWechatMessageListener> listeners;
    private final WechatClient                        wechatClient;
    private final ExecutorService                     executor = Executors.newVirtualThreadPerTaskExecutor();

    public CallbackWechatMessageHandler(ObjectProvider<AbstractWechatMessageListener> listeners,
                                        WechatClient wechatClient) {
        this.listeners    = listeners.orderedStream()
                                     .toList();
        this.wechatClient = wechatClient;
    }

    @Override
    public void handle(WechatCallBackMessage message) {
        // 如果消息的发送者不是当前绑定的微信账号，则不处理
        if (!StrUtil.equals(message.getWxid(), WechatConfigHolder.getBindWxid())) {
            return;
        }
        // 异步处理消息
        listeners.stream()
                 .filter(listener -> listener.getConfig()
                                             .isEnable())
                 .filter(listener -> listener.support(message.getEvent()))
                 .forEach(listener -> executor.execute(() -> listener.listener(message.getData(), wechatClient)));
    }
}
