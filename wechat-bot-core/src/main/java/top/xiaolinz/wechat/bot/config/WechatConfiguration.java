package top.xiaolinz.wechat.bot.config;

import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.HTTP.OkConfig;
import cn.zhxu.okhttps.OkHttps;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信客户端配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/3
 */
@EnableConfigurationProperties(WeChatProperties.class)
@Configuration
@RequiredArgsConstructor
public class WechatConfiguration {

    private final WeChatProperties weChatProperties;

    /**
     * okhttp 配置
     *
     * @return {@link OkConfig }
     * @author huangmuhong
     * @date 2024/07/17
     */
    @Bean
    public OkConfig okConfig() {
        return (OkHttpClient.Builder builder) -> {
            builder.connectionPool(new ConnectionPool(40, 5, TimeUnit.MINUTES));
            builder.connectTimeout(weChatProperties.getTimeout(), TimeUnit.MILLISECONDS);
            builder.readTimeout(weChatProperties.getTimeout(), TimeUnit.MILLISECONDS);
            final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.level(Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        };
    }

    /**
     * 千寻 http 客户端
     *
     * @return {@link HTTP }
     * @author huangmuhong
     * @date 2024/07/17
     */
    @Bean
    public HTTP qianXunHttpClient(OkConfig okConfig) {
        return OkHttps.newBuilder()
                      .baseUrl(weChatProperties.getHost())
                      .config(okConfig)
                      .build();
    }
}
