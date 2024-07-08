package top.xiaolinz.wechat.bot.core.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.dromara.hutool.core.lang.Assert;
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

    private static final InheritableThreadLocal<String> WXID = new InheritableThreadLocal<>();
    private final        WeChatConfig                   weChatConfig;

    /**
     * 设置微信ID
     *
     * @param wxid 微信ID
     */
    public static void setWxid(String wxid) {
        WXID.set(wxid);
    }

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
            Assert.notBlank(WXID.get(), "执行 wechat 调用 wxid 不能为空");
            template.header("wxid", WXID.get());
            template.header("secret", weChatConfig.getSecret());
        };
    }
}
