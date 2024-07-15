package top.xiaolinz.wechat.bot.config;

import lombok.RequiredArgsConstructor;
import org.dromara.hutool.http.client.ClientConfig;
import org.dromara.hutool.http.client.engine.ClientEngine;
import org.dromara.hutool.http.client.engine.okhttp.OkHttpEngine;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信客户端配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/3
 */
@EnableConfigurationProperties(WeChatProperties.class)
@Component
@RequiredArgsConstructor
public class WechatConfiguration {

    private final WeChatProperties weChatProperties;

    /**
     * 客户端配置
     *
     * @return {@link ClientConfig }
     * @author huangmuhong
     * @date 2024/07/14
     */
    @Bean
    public ClientConfig clientConfig() {
        return ClientConfig.of()
                           .setTimeout(weChatProperties.getTimeout())
                           .setReadTimeout(weChatProperties.getTimeout());
    }

    /**
     * 客户端引擎
     *
     * @param config 配置
     * @return {@link ClientEngine }
     * @author huangmuhong
     * @date 2024/07/14
     */
    @Bean
    public ClientEngine clientEngine(ClientConfig config) {
        final OkHttpEngine httpEngine = new OkHttpEngine();
        httpEngine.init(config);
        return httpEngine;
    }
}
