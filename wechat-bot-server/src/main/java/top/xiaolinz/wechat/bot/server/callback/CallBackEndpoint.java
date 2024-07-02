package top.xiaolinz.wechat.bot.server.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xiaolinz.wechat.bot.core.event.WechatCallBackRequest;

/**
 * 回调端点
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/6/30
 */
@Slf4j
@RequestMapping("wechat/callback")
@RestController
public class CallBackEndpoint {

    @PostMapping
    public void callback(@RequestBody WechatCallBackRequest event) {
        log.info("收到 WeChat 回调事件: {}", event);
    }

}
