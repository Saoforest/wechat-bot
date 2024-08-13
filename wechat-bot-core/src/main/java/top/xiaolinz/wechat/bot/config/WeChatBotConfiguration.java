package top.xiaolinz.wechat.bot.config;

import static top.xiaolinz.wechat.bot.core.constants.WechatBot.WECHAT_BOT_CONFIG_PREFIX;

import com.dtflys.forest.config.ForestConfiguration;
import lombok.RequiredArgsConstructor;
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

/**
 * 微信配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/23
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class WeChatBotConfiguration {

    private final ConfigurableApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix = WECHAT_BOT_CONFIG_PREFIX)
    public WeChatBotConfig weChatConfig() {
        return new WeChatBotConfig();
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
     * 回调微信请求处理程序
     *
     * @param messageListeners 消息监听器
     * @return {@link CallbackWechatRequestHandler }
     * @author huangmuhong
     * @date 2024/08/04
     */
    @Bean("callbackWechatRequestHandler")
    @ConditionalOnMissingBean
    public CallbackWechatRequestHandler callbackWechatRequestHandler(
        ObjectProvider<WechatCallbackListener<?>> messageListeners) {
        return new CallbackWechatRequestHandler(messageListeners);
    }

}
