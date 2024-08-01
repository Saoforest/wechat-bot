package top.xiaolinz.wechat.bot.core;

/**
 * 微信消息处理器
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/28
 */
public interface WechatMessageHandler<T> {

    /**
     * 处理消息
     *
     * @param message 信息
     * @author huangmuhong
     * @date 2024/07/28
     */
    void handle(T message);
}
