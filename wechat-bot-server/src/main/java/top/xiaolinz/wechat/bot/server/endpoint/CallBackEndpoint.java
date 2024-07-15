package top.xiaolinz.wechat.bot.server.endpoint;

import com.yomahub.liteflow.core.FlowExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xiaolinz.wechat.bot.core.callback.WechatCallbackContext;
import top.xiaolinz.wechat.bot.core.model.WechatCallBack;

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

    private final FlowExecutor flowExecutor;

    /**
     * 回调
     *
     * @param callback 事件
     * @author huangmuhong
     * @date 2024/07/10
     */
    @PostMapping
    public void callback(@RequestBody WechatCallBack callback) {
        flowExecutor.execute2Future("callback", callback, WechatCallbackContext.class);
    }

}
