package top.xiaolinz.wechat.bot.core;

import top.xiaolinz.wechat.bot.core.enums.CallbackTypeEnum;
import top.xiaolinz.wechat.bot.core.model.callback.Callback;

/**
 * 千寻框架的回调监听器
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 */
public interface CallbackListener<T extends Callback<?>> {

    /**
     * 监听
     *
     * @param callback 回调
     * @author huangmuhong
     * @date 2024/07/08
     */
    void listen(T callback) throws Exception;

    /**
     * 支持类型
     *
     * @return {@link CallbackTypeEnum }
     * @author huangmuhong
     * @date 2024/07/08
     */
    CallbackTypeEnum supportType();

}
