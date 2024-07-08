package top.xiaolinz.wechat.bot.core;

import com.yomahub.liteflow.core.FlowExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.enums.EventTypeEnum;
import top.xiaolinz.wechat.bot.core.process.WechatEventContext;
import top.xiaolinz.wechat.bot.endpoint.WechatEventDispatch;
import top.xiaolinz.wechat.bot.endpoint.model.WechatCallBackRequest;

/**
 * 基于流程的微信事件调度器
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 * @see WechatEventDispatch
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class LiteFlowWechatEventDispatch implements WechatEventDispatch {

    private final FlowExecutor flowExecutor;

    @Override
    public void dispatch(WechatCallBackRequest request) {

        final EventTypeEnum type = EventTypeEnum.getEventType(request.getEvent());
        if (type == null) {
            log.info("不支持分发的事件类型: {}", request.getEvent());
            return;
        }

        final WechatEventContext context = new WechatEventContext();
        context.setFromWxid(request.getWxid());
        context.setData(request.getData());
        context.setType(type);

        // 执行流程
        flowExecutor.execute2Future("wechatEventHandlerChain", null, context);
    }

}
