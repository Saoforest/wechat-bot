package top.xiaolinz.wechat.bot.core;

import com.yomahub.liteflow.core.FlowExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.process.WechatEventContext;
import top.xiaolinz.wechat.bot.endpoint.WechatEventHandler;
import top.xiaolinz.wechat.bot.endpoint.model.WechatCallBackRequest;

/**
 * 默认微信事件处理器
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 * @see WechatEventHandler
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultWechatEventHandler implements WechatEventHandler {

    private final FlowExecutor flowExecutor;

    @Override
    public void publishEvent(WechatCallBackRequest request) {
        final long event = request.getEvent();
        flowExecutor.execute2Future("wechatEventHandlerChain", request, WechatEventContext.class);
    }

}
