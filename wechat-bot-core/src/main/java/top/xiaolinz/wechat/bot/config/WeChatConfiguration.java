package top.xiaolinz.wechat.bot.config;

import com.dtflys.forest.config.ForestConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.xiaolinz.wechat.bot.core.CallbackWechatMessageHandler;
import top.xiaolinz.wechat.bot.core.QianXunWechatClient;
import top.xiaolinz.wechat.bot.core.WechatClient;
import top.xiaolinz.wechat.bot.core.WechatConfigHolder;
import top.xiaolinz.wechat.bot.core.message.AbstractWechatMessageListener;

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
public class WeChatConfiguration implements InitializingBean {

    private final WeChatProperties weChatProperties;

    /**
     * 微信客户端
     *
     * @param forestClient 森林客户
     * @return {@link WechatClient }
     * @author huangmuhong
     * @date 2024/08/04
     */
    @Bean
    @ConditionalOnMissingBean
    public WechatClient wechatClient(
                                     ForestConfiguration forestClient) {
        return new QianXunWechatClient(forestClient);
    }

    /**
     * 回调微信信息处理程序
     *
     * @param messageListeners 消息监听器
     * @return {@link CallbackWechatMessageHandler }
     * @author huangmuhong
     * @date 2024/08/04
     */
    @Bean("callbackWechatMessageHandler")
    @ConditionalOnMissingBean
    public CallbackWechatMessageHandler callbackWechatMessageHandler(
        ObjectProvider<AbstractWechatMessageListener> messageListeners) {
        return new CallbackWechatMessageHandler(messageListeners);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
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
