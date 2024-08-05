package top.xiaolinz.wechat.bot.plugin.chat.extension.dify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * dify 聊天完成响应
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/2
 */
@Data
public class DifyChatCompletionResponse {
    private String                             event;
    @JsonProperty("message_id")
    private String                             messageId;
    @JsonProperty("conversation_id")
    private String                             conversationId;
    private String                             mode;
    private String                             answer;
    private DifyChatCompletionResponseMetadata metadata;
    @JsonProperty("created_at")
    private Long                               createdAt;

}
