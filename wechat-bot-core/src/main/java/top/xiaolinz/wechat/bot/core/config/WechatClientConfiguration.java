package top.xiaolinz.wechat.bot.core.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.dromara.hutool.core.lang.Assert;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import top.xiaolinz.wechat.bot.core.client.QianXunWechatClient.WxidHolder;

/**
 * 微信客户端配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/3
 */
@EnableConfigurationProperties(WeChatConfig.class)
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
            final String wxid = WxidHolder.get();
            Assert.notBlank(wxid, "执行 wechat 调用 wxid 不能为空");
            template.header("wxid", wxid);
            template.header("secret", weChatConfig.getSecret());
        };
    }
}
