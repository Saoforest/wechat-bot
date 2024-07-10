package top.xiaolinz.wechat.bot.core.flow.callback;

import com.yomahub.liteflow.thread.ExecutorBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * liteflow虚拟线程池构建器
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/6
 * @see ExecutorBuilder
 */
public class VirtualThreadExecutorBuilder implements ExecutorBuilder {
    @Override
    public ExecutorService buildExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
