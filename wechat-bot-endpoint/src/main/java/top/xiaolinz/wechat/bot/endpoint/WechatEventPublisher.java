package top.xiaolinz.wechat.bot.endpoint;

import top.xiaolinz.wechat.bot.endpoint.model.WechatCallBackRequest;

/**
 * 微信事件发布程序 用于将微信的回调请求发布为事件 事件将被分发到所有订阅者
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/1
 */
public interface WechatEventPublisher {

    /**
     * 发布事件
     *
     * @param request 要求
     * @author huangmuhong
     * @date 2024/07/02
     */
    void publishEvent(WechatCallBackRequest request);
}
