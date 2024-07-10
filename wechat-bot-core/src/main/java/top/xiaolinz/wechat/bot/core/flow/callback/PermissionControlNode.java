package top.xiaolinz.wechat.bot.core.flow.callback;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.RequiredArgsConstructor;
import top.xiaolinz.wechat.bot.core.WechatRobot;

/**
 * 权限控制节点
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 * @see NodeComponent
 */
@LiteflowComponent("permissionControlNode")
@RequiredArgsConstructor
public class PermissionControlNode extends NodeComponent {

    private final WechatRobot robot;

    @Override
    public void process() throws Exception {

    }
}
