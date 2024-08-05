package top.xiaolinz.wechat.bot.plugin.chat.extension.dify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * dify用量
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/2
 */
@Data
public class DifyUsage {
    @JsonProperty("prompt_tokens")
    private Long   promptTokens;
    @JsonProperty("prompt_unit_price")
    private String promptUnitPrice;
    @JsonProperty("prompt_price_unit")
    private String promptPriceUnit;
    @JsonProperty("prompt_price")
    private String promptPrice;
    @JsonProperty("completion_tokens")
    private Long   completionTokens;
    @JsonProperty("completion_unit_price")
    private String completionUnitPrice;
    @JsonProperty("completion_price_unit")
    private String completionPriceUnit;
    @JsonProperty("completion_price")
    private String completionPrice;
    @JsonProperty("total_tokens")
    private Long   totalTokens;
    @JsonProperty("total_price")
    private String totalPrice;
    private String currency;
    private Double latency;

}
