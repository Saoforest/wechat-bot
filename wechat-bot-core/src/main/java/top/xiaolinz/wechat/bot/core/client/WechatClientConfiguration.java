package top.xiaolinz.wechat.bot.core.client;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信客户端配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/3
 */
@Component
@RequiredArgsConstructor
public class WechatClientConfiguration {

    private final WeChatConfig weChatConfig;

    /**
     * 微信header拦截器
     *
     * @return {@link RequestInterceptor }
     * @author huangmuhong
     * @date 2024/07/03
     */
    @Bean
    public RequestInterceptor wechatHeaderInterceptor() {
        return template -> {
            template.header("secret", weChatConfig.getSecret());
        };
    }
}
