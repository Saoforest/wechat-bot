package top.xiaolinz.wechat.bot.core.flow.callback;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import top.xiaolinz.wechat.bot.core.client.QianXunWechatClient;
import top.xiaolinz.wechat.bot.core.client.QianXunWechatClient.WxidHolder;
import top.xiaolinz.wechat.bot.core.enums.EventTypeEnum;
import top.xiaolinz.wechat.bot.core.model.vo.WechatCallBackRequest;

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
    private final QianXunWechatClient qianXunWechatClient;

    public ContextHandleNode(QianXunWechatClient qianXunWechatClient) {
        this.qianXunWechatClient = qianXunWechatClient;
    }

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

        context.setFromWxid(data.getWxid());
        context.setData(data.getData());
        context.setType(type);

        // 设置当前操作的微信ID
        WxidHolder.set(data.getWxid());
    }
}
