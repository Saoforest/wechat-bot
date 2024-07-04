package top.xiaolinz.wechat.bot.core;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.enums.EventType;
import top.xiaolinz.wechat.bot.core.event.WechatEvent;
import top.xiaolinz.wechat.bot.core.factory.WechatEventFactory;
import top.xiaolinz.wechat.bot.core.factory.WechatEventFactoryProvider;
import top.xiaolinz.wechat.bot.endpoint.WechatEventPublisher;
import top.xiaolinz.wechat.bot.endpoint.model.WechatCallBackRequest;

/**
 * 默认微信事件发布者
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 * @see WechatEventPublisher
 */
@RequiredArgsConstructor
@Service
public class DefaultWechatEventPublisher implements WechatEventPublisher, ApplicationEventPublisherAware {

    private final WechatEventFactoryProvider eventFactoryProvider;
    private       ApplicationEventPublisher  publisher;

    @Override
    public void publishEvent(WechatCallBackRequest request) {
        final long                  event       = request.getEvent();
        final EventType             type        = EventType.getEventType(event);
        final WechatEventFactory<?> factory     = eventFactoryProvider.getWechatEventFactory(type);
        final WechatEvent           wechatEvent = factory.createEvent(request);
        publisher.publishEvent(wechatEvent);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        publisher = applicationEventPublisher;
    }
}
