package top.xiaolinz.wechat.bot.core;

import com.yomahub.liteflow.core.FlowExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.flow.callback.WechatEventContext;
import top.xiaolinz.wechat.bot.core.model.vo.WechatCallBackRequest;

/**
 * 基于流程的回调处理器
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 * @see CallbackFacade
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class FlowCallbackFacade implements CallbackFacade {

    private final FlowExecutor flowExecutor;

    @Override
    public void handle(WechatCallBackRequest request) {
        // 执行流程
        flowExecutor.execute2Future("wechatEventHandlerChain", request, WechatEventContext.class);
    }

}
