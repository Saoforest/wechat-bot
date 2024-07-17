package top.xiaolinz.wechat.bot.core.callback;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import top.xiaolinz.wechat.bot.core.CallbackListener;
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
@SuppressWarnings("all")
@LiteflowComponent("extensionExecuteNode")
public class ExtensionExecuteNode extends NodeComponent {

    private static final Logger                 log = LoggerFactory.getLogger(ExtensionExecuteNode.class);
    private final        List<CallbackListener> callbackListeners;

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

        // 创建虚拟线程池
        final ExecutorService executor =
            TtlExecutors.getTtlExecutorService(Executors.newVirtualThreadPerTaskExecutor());

        // 使用线程池执行
        for (final CallbackListener callbackListener : extension) {
            executor.submit(() -> {
                try {
                    callbackListener.listen(data);
                } catch (Exception e) {
                    log.error("扩展执行失败", e);
                }
            });
        }
    }
}
