package top.xiaolinz.wechat.bot.core.callback;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import top.xiaolinz.wechat.bot.core.CallbackListener;
import top.xiaolinz.wechat.bot.core.WxidHolder;
import top.xiaolinz.wechat.bot.core.enums.CallbackTypeEnum;
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

    private final List<CallbackListener> callbackListeners;

    public ExtensionExecuteNode(ObjectProvider<CallbackListener> provider) {
        callbackListeners = provider.orderedStream()
                                    .toList();
    }

    @Override
    public void process() throws Exception {
        final WechatCallbackContext context = getContextBean(WechatCallbackContext.class);
        final CallbackTypeEnum      type    = context.getType();

        // 获取支持消息类型的扩展
        final List<CallbackListener> extension = callbackListeners.stream()
                                                                  .filter(
                                                                      callbackListener -> callbackListener.supportType()
                                                                                                          .equals(type))
                                                                  .toList();

        // 转换数据类型
        final Callback<?> data = (Callback<?>)context.getData()
                                                     .toJavaObject(type.getEventClass());

        // 执行具体的回调监听器
        for (final CallbackListener callbackListener : extension) {
            callbackListener.listen(data);
        }

        // 清理 wxid
        WxidHolder.clear();
    }
}
