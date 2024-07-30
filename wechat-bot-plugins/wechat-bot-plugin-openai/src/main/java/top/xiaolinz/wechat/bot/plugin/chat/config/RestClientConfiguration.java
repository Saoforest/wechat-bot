package top.xiaolinz.wechat.bot.plugin.chat.config;

import org.springframework.boot.autoconfigure.web.client.RestClientBuilderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

/**
 * 结果客户端配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/15
 */
@Configuration
public class RestClientConfiguration {
    @Bean
    public OkHttp3ClientHttpRequestFactory httpRequestFactory() {
        OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory();
        requestFactory.setConnectTimeout(600000);
        requestFactory.setReadTimeout(600000);
        requestFactory.setWriteTimeout(100000);

        return requestFactory;
    }

    @Bean
    @Scope("prototype")
    RestClient.Builder restClientBuilder(RestClientBuilderConfigurer restClientBuilderConfigurer,
                                         OkHttp3ClientHttpRequestFactory requestFactory) {
        RestClient.Builder builder = RestClient.builder()
                                               .requestFactory(requestFactory);
        return restClientBuilderConfigurer.configure(builder);
    }
}
