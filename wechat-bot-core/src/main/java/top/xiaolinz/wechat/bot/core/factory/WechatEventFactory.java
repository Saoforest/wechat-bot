package top.xiaolinz.wechat.bot.core.factory;

import top.xiaolinz.wechat.bot.core.enums.EventType;
import top.xiaolinz.wechat.bot.core.event.WechatEvent;
import top.xiaolinz.wechat.bot.endpoint.model.WechatCallBackRequest;

/**
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/5
 */
public interface WechatEventFactory<T extends WechatEvent> {

    /**
     * 创建事件
     *
     * @param request 要求
     * @return {@link T }
     * @author huangmuhong
     * @date 2024/07/05
     */
    T createEvent(WechatCallBackRequest request);

    /**
     * 支持
     *
     * @param eventType 事件类型
     * @return boolean
     * @author huangmuhong
     * @date 2024/07/05
     */
    boolean support(EventType eventType);

}
