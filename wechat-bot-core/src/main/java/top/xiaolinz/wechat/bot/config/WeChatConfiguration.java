package top.xiaolinz.wechat.bot.config;

import static top.xiaolinz.wechat.bot.core.constants.Wechat.WECHAT_PROPERTIES_PREFIX;

import com.dtflys.forest.config.ForestConfiguration;
import lombok.RequiredArgsConstructor;
import org.dromara.hutool.core.lang.Assert;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.xiaolinz.wechat.bot.core.CallbackWechatRequestHandler;
import top.xiaolinz.wechat.bot.core.QianXunWechatClient;
import top.xiaolinz.wechat.bot.core.WechatCallbackListener;
import top.xiaolinz.wechat.bot.core.WechatClient;
import top.xiaolinz.wechat.bot.core.WechatManager;

/**
 * 微信配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/23
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class WeChatConfiguration implements InitializingBean {

    private final ConfigurableApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix = WECHAT_PROPERTIES_PREFIX)
    public WeChatConfig weChatConfig() {
        return new WeChatConfig();
    }

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
     * @return {@link CallbackWechatRequestHandler }
     * @author huangmuhong
     * @date 2024/08/04
     */
    @Bean("callbackWechatMessageHandler")
    @ConditionalOnMissingBean
    public CallbackWechatRequestHandler callbackWechatMessageHandler(
        ObjectProvider<WechatCallbackListener<?>> messageListeners) {
        return new CallbackWechatRequestHandler(messageListeners);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 配置注入管理
        final WeChatConfig weChatConfig = applicationContext.getBean(WeChatConfig.class);
        Assert.notNull(weChatConfig, "WeChatConfig bean not found");
        WechatManager.setWeChatConfig(weChatConfig);
        // client 注入管理
        final WechatClient wechatClient = applicationContext.getBean(WechatClient.class);
        Assert.notNull(wechatClient, "WechatClient bean not found");
        WechatManager.setWechatClient(wechatClient);
        // 回调消息处理器注入管理
        final CallbackWechatRequestHandler callbackWechatMessageHandler =
            applicationContext.getBean("callbackWechatMessageHandler", CallbackWechatRequestHandler.class);
        Assert.notNull(callbackWechatMessageHandler, "CallbackWechatMessageHandler bean not found");
        WechatManager.setWechatCallbackMessageHandler(callbackWechatMessageHandler);

    }
}
