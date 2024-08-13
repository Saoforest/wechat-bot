package top.xiaolinz.wechat.bot.server.endpoint;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xiaolinz.wechat.bot.core.WechatRequestHandler;
import top.xiaolinz.wechat.bot.core.model.WechatCallBackRequest;

/**
 * 回调端点
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/6/30
 */
@RequestMapping("callback")
@RestController
public class CallBackEndpoint {

    private final WechatRequestHandler<WechatCallBackRequest> handler;

    public CallBackEndpoint(
        @Qualifier("callbackWechatMessageHandler") WechatRequestHandler<WechatCallBackRequest> handler) {
        this.handler = handler;
    }

    /**
     * 回调消息
     *
     * @param message 消息
     * @author huangmuhong
     * @date 2024/07/10
     */
    @PostMapping
    public void callback(@RequestBody WechatCallBackRequest message) {
        handler.handle(message);
    }

}
