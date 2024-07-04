package top.xiaolinz.wechat.bot.core.factory;

import top.xiaolinz.wechat.bot.core.enums.EventType;

/**
 * 微信事件工厂提供商
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/5
 */
public interface WechatEventFactoryProvider {

    /**
     * 获取微信事件工厂
     *
     * @param eventType 事件类型
     * @return {@link WechatEventFactory}
     * @author huangmuhong
     * @date 2024/07/05
     */
    WechatEventFactory<?> getWechatEventFactory(EventType eventType);

}
