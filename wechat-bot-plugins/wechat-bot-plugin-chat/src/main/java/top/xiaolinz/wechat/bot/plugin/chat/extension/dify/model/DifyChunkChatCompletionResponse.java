package top.xiaolinz.wechat.bot.plugin.chat.extension.dify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * dify 块聊天完成响应
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/2
 */
@Data
public class DifyChunkChatCompletionResponse {
    @JsonProperty("task_id")
    private String taskId;
    private String event;
    @JsonProperty("message_id")
    private String messageId;
    @JsonProperty("conversation_id")
    private String conversationId;
    private String answer;
    @JsonProperty("created_at")
    private Long   createdAt;

}
