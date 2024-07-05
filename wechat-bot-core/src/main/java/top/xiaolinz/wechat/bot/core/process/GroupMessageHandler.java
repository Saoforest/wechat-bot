package top.xiaolinz.wechat.bot.core.process;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * 群消息处理程序
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/6
 * @see NodeComponent
 */
@Slf4j
@LiteflowComponent("groupMessageHandler")
public class GroupMessageHandler extends NodeComponent {
    @Override
    public void process() throws Exception {
        final WechatEventContext context = getContextBean(WechatEventContext.class);
    }
}
