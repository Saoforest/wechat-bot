package top.xiaolinz.wechat.bot.core;

import top.xiaolinz.wechat.bot.core.enums.WechatMessageTypeEnum;
import top.xiaolinz.wechat.bot.core.model.callback.WechatCallback;

/**
 * 微信回调监听
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/26
 */
public interface WechatCallbackListener<T extends WechatCallback<?>> {

    /**
     * 监听
     *
     * @param data 数据
     * @author huangmuhong
     * @date 2024/07/26
     */
    void listener(T data);

    /**
     * 是否是支持的消息类型
     *
     * @param type 类型
     * @return boolean
     * @author huangmuhong
     * @date 2024/07/28
     */
    boolean support(WechatMessageTypeEnum type);

}
