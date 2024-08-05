package top.xiaolinz.wechat.bot.plugin.chat.extension.dify.api;

import lombok.Data;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.ai.util.api.ApiUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Dify API
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/2
 */
@Data
public class DifyApi {
    private static final String     DIFY_CHAT_COMPLETIONS_PATH = "/v1/chat-messages";
    private final        RestClient restClient;
    private final        WebClient  webClient;

    public DifyApi(String baseUrl, String openAiToken) {
        restClient = RestClient.builder()
                               .baseUrl(baseUrl)
                               .defaultHeaders(ApiUtils.getJsonContentHeaders(openAiToken))
                               .defaultStatusHandler(RetryUtils.DEFAULT_RESPONSE_ERROR_HANDLER)
                               .build();

        webClient = WebClient.builder()
                             .baseUrl(baseUrl)
                             .defaultHeaders(ApiUtils.getJsonContentHeaders(openAiToken))
                             .build();
    }

}
