package top.xiaolinz.wechat.bot.core.callback;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.RequiredArgsConstructor;
import top.xiaolinz.wechat.bot.core.WechatHolder;
import top.xiaolinz.wechat.bot.core.enums.CallbackTypeEnum;
import top.xiaolinz.wechat.bot.core.model.WechatCallBack;

/**
 * 上下文初始化节点
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 * @see NodeComponent
 */
@RequiredArgsConstructor
@LiteflowComponent("contextHandlerNode")
public class ContextInitializationNode extends NodeComponent {

    @Override
    public void process() throws Exception {
        final WechatCallBack        data    = getRequestData();
        final WechatCallbackContext context = getFirstContextBean();

        final CallbackTypeEnum type = CallbackTypeEnum.getEventType(data.getEvent());
        if (type == null) {
            // 如果不支持的事件类型，直接结束
            setIsEnd(true);
            return;
        }

        context.setFromWxid(data.getWxid());
        context.setData(data.getData());
        context.setType(type);

        // 设置当前操作的微信ID
        WechatHolder.setWxid(data.getWxid());
    }
}
