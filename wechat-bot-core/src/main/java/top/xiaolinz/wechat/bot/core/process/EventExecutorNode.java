package top.xiaolinz.wechat.bot.core.process;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import top.xiaolinz.wechat.bot.core.WechatExtension;
import top.xiaolinz.wechat.bot.core.enums.EventTypeEnum;

/**
 * 事件执行器节点
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 */
@LiteflowComponent("eventExecutorNode")
public class EventExecutorNode extends NodeComponent {

    private final List<WechatExtension> wechatExtensions;

    public EventExecutorNode(ObjectProvider<WechatExtension> provider) {
        wechatExtensions = provider.orderedStream().toList();
    }

    @Override
    public void process() throws Exception {
        final WechatEventContext context = getFirstContextBean();
        final EventTypeEnum      type    = context.getType();

        // 获取支持消息类型的扩展
        final List<WechatExtension> extensions =
            wechatExtensions.stream().filter(extension -> Arrays.asList(extension.supportTypes()).contains(type))
                .toList();

        // 转换 data
        final Object data = context.getData().to(type.getEventClass());

        // 执行扩展
        for (WechatExtension extension : extensions) {
            extension.process(data);
        }
    }
}
