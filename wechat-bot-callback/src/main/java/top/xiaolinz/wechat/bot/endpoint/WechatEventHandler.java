package top.xiaolinz.wechat.bot.endpoint;

import top.xiaolinz.wechat.bot.endpoint.model.WechatCallBackRequest;

/**
 * 微信事件处理器
 *
 * 处理回调事件，分发给各类型的事件处理节点
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/1
 */
public interface WechatEventHandler {

    /**
     * 发布事件
     *
     * @param request 要求
     * @author huangmuhong
     * @date 2024/07/02
     */
    void publishEvent(WechatCallBackRequest request);
}
