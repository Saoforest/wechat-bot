package top.xiaolinz.wechat.bot.core.flow;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import top.xiaolinz.wechat.bot.core.CallbackListener;
import top.xiaolinz.wechat.bot.core.enums.EventTypeEnum;
import top.xiaolinz.wechat.bot.core.model.callback.Callback;

/**
 * 扩展执行节点
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 * @see NodeComponent
 */
@LiteflowComponent("extensionExecuteNode")
public class ExtensionExecuteNode extends NodeComponent {

    private final List<CallbackListener<Callback<?>>> callbackListeners;

    public ExtensionExecuteNode(ObjectProvider<CallbackListener<Callback<?>>> provider) {
        callbackListeners = provider.orderedStream()
                                    .toList();
    }

    @Override
    public void process() throws Exception {
        final WechatEventContext context = getFirstContextBean();
        final EventTypeEnum      type    = context.getType();

        // 获取支持消息类型的扩展
        final List<CallbackListener<Callback<?>>> extension = callbackListeners.stream()
                                                                               .filter(
                                                                                   e -> Arrays.asList(e.supportTypes())
                                                                                              .contains(type))
                                                                               .toList();

        // 转换数据类型
        final Callback<?> data = (Callback<?>)context.getData()
                                                     .toJavaObject(type.getEventClass());

        // 执行具体的回调监听器
        for (final CallbackListener<Callback<?>> callbackListener : extension) {
            callbackListener.process(data);
        }
    }
}
