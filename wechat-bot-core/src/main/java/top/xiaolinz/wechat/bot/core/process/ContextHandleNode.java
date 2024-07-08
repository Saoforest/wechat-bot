package top.xiaolinz.wechat.bot.core.process;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import top.xiaolinz.wechat.bot.core.config.WechatClientConfiguration;
import top.xiaolinz.wechat.bot.core.enums.EventTypeEnum;
import top.xiaolinz.wechat.bot.endpoint.model.WechatCallBackRequest;

/**
 * 上下文处理节点
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 * @see NodeComponent
 */
@LiteflowComponent("contextHandlerNode")
public class ContextHandleNode extends NodeComponent {
    @Override
    public void process() throws Exception {
        final WechatCallBackRequest data    = getRequestData();
        final WechatEventContext    context = getFirstContextBean();

        final EventTypeEnum type = EventTypeEnum.getEventType(data.getEvent());
        if (type == null) {
            // 如果不支持的事件类型，直接结束
            setIsEnd(true);
            return;
        }

        WechatClientConfiguration.setWxid(data.getWxid());
        context.setFromWxid(data.getWxid());
        context.setData(data.getData());
        context.setType(type);
    }
}
