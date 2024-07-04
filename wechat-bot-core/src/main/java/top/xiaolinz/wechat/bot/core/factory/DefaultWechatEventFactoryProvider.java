package top.xiaolinz.wechat.bot.core.factory;

import java.util.List;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.enums.EventType;

/**
 * 默认微信事件工厂提供商
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/5
 * @see WechatEventFactoryProvider
 */
@Service
public class DefaultWechatEventFactoryProvider implements WechatEventFactoryProvider {

    private final List<WechatEventFactory<?>> factories;

    public DefaultWechatEventFactoryProvider(List<WechatEventFactory<?>> factories) {
        this.factories = factories;
    }

    @Override
    public WechatEventFactory<?> getWechatEventFactory(EventType eventType) {
        return factories.stream().filter(factory -> factory.support(eventType)).findFirst().orElse(null);
    }
}
