package top.xiaolinz.wechat.bot.server.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xiaolinz.wechat.bot.core.CallbackFacade;
import top.xiaolinz.wechat.bot.core.model.vo.WechatCallBackRequest;

/**
 * 回调端点
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/6/30
 */
@Slf4j
@RequestMapping("callback")
@RestController
@RequiredArgsConstructor
public class CallBackEndpoint {

    private final CallbackFacade callbackFacade;

    @PostMapping
    public void callback(@RequestBody WechatCallBackRequest event) {
        callbackFacade.handle(event);
    }

}
