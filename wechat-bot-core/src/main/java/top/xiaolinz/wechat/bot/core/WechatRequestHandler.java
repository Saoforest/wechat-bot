package top.xiaolinz.wechat.bot.core;

/**
 * 微信请求处理器
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/28
 */
public interface WechatRequestHandler<T> {

    /**
     * 处理请求
     *
     * @param message 信息
     * @author huangmuhong
     * @date 2024/07/28
     */
    void handle(T message);
}
