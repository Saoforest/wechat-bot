package top.xiaolinz.wechat.bot.core;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.model.WechatCallBackMessage;

/**
 * 回调微信信息处理程序
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/28
 * @see WechatMessageHandler
 */
@Service("callbackWechatMessageHandler")
public class CallbackWechatMessageHandler implements WechatMessageHandler<WechatCallBackMessage> {
    private final List<AbstractWechatMessageListener> listeners;
    private final WechatClient                        wechatClient;

    public CallbackWechatMessageHandler(ObjectProvider<AbstractWechatMessageListener> listeners,
                                        WechatClient wechatClient) {
        this.listeners    = listeners.orderedStream()
                                     .toList();
        this.wechatClient = wechatClient;
    }

    @Override
    public void handle(WechatCallBackMessage message) {
        final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        listeners.parallelStream()
                 .filter(listener -> listener.getConfig()
                                             .isEnable())
                 .filter(listener -> listener.support(message.getEvent()))
                 .forEach(listener -> executor.execute(() -> listener.listener(message.getData(), wechatClient)));
    }
}
