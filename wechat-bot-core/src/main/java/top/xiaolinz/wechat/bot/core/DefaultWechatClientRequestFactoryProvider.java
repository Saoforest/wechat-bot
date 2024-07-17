package top.xiaolinz.wechat.bot.core;

import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.factory.WechatClientRequestFactory;

/**
 * 默认微信客户端请求工厂提供商
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/12
 * @see WechatClientRequestFactoryProvider
 */
@Service
public class DefaultWechatClientRequestFactoryProvider implements WechatClientRequestFactoryProvider {

    private final List<WechatClientRequestFactory<?>> wechatClientRequestFactories;

    public DefaultWechatClientRequestFactoryProvider(
        ObjectProvider<WechatClientRequestFactory<?>> wechatClientRequestFactories) {
        this.wechatClientRequestFactories = wechatClientRequestFactories.orderedStream()
                                                                        .toList();
    }

    @Override
    public WechatClientRequestFactory<?> getWechatClientRequestFactory(String type) {
        return wechatClientRequestFactories.stream()
                                           .filter(factory -> factory.support(type))
                                           .findFirst()
                                           .orElseThrow(
                                               () -> new IllegalArgumentException("Unsupported type: " + type));
    }
}
