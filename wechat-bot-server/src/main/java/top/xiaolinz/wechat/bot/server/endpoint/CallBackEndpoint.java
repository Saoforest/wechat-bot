package top.xiaolinz.wechat.bot.server.endpoint;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xiaolinz.wechat.bot.core.WechatManager;
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

    /**
     * 回调消息
     *
     * @param message 消息
     * @author huangmuhong
     * @date 2024/07/10
     */
    @PostMapping
    public void callback(@RequestBody WechatCallBackRequest message) {
        WechatManager.getCallbackWechatRequestHandler()
                     .handle(message);
    }

}
