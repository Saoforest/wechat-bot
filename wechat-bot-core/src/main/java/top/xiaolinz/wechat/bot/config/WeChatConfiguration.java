package top.xiaolinz.wechat.bot.config;

import com.dtflys.forest.config.ForestConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.xiaolinz.wechat.bot.core.QianXunWechatClient;
import top.xiaolinz.wechat.bot.core.WechatClient;
import top.xiaolinz.wechat.bot.core.WechatConfigHolder;
import top.xiaolinz.wechat.bot.core.factory.WechatClientRequestFactoryProvider;

/**
 * 微信配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/23
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(WeChatProperties.class)
public class WeChatConfiguration implements SmartInitializingSingleton {

    private final WeChatProperties weChatProperties;

    @Bean
    @ConditionalOnMissingBean
    public WechatClient wechatClient(WechatClientRequestFactoryProvider wechatClientRequestFactoryProvider,
                                     ForestConfiguration forestClient) {
        return new QianXunWechatClient(wechatClientRequestFactoryProvider, forestClient);
    }

    @Override
    public void afterSingletonsInstantiated() {
        final PropertyMapper mapper = PropertyMapper.get()
                                                    .alwaysApplyingWhenNonNull();
        mapper.from(weChatProperties::getWxid)
              .to(WechatConfigHolder::setBindWxid);
        mapper.from(weChatProperties::getHost)
              .to(WechatConfigHolder::setHost);
        mapper.from(weChatProperties::getSecret)
              .to(WechatConfigHolder::setSecret);
    }
}
