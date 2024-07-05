package top.xiaolinz.wechat.bot.core.process;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;
import top.xiaolinz.wechat.bot.core.enums.EventType;
import top.xiaolinz.wechat.bot.endpoint.model.WechatCallBackRequest;

/**
 * 微信回调处理链
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/5
 * @see NodeSwitchComponent
 */
@LiteflowComponent("wechatEventHandlerControl")
public class WechatEventHandlerControl extends NodeSwitchComponent {

    @Override
    public String processSwitch() throws Exception {
        // 初始化上下文数据
        final WechatEventContext    context = getContextBean(WechatEventContext.class);
        final WechatCallBackRequest data    = getRequestData();
        final EventType             type    = EventType.getEventType(data.getEvent());
        context.setType(type);
        context.setFromWxid(data.getWxid());
        context.setData(data.getData());

        return type.getNodeName();
    }
}
