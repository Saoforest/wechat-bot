package top.xiaolinz.wechat.bot.plugin.chat.extension.dify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.Getter;

/**
 * Dify聊天请求
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/2
 */
@Data
public class DifyChatCompletionRequest {
    private Object         inputs;
    private String         query;
    @JsonProperty("response_mode")
    private String         responseMode;
    @JsonProperty("conversation_id")
    private String         conversationId;
    private String         user;
    private List<DifyFile> files;

    /**
     * 响应模式
     *
     * @author huangmuhong
     * @version 1.0.0
     * @date 2024/08/02
     * @see Enum
     */
    @Getter
    public enum ResponseMode {
        /**
         * 阻塞
         */
        @JsonProperty("blocking") BLOCKING,
        /**
         * 流媒体
         */
        @JsonProperty("streaming") STREAMING
    }

    /**
     * 解析文件
     *
     * @author huangmuhong
     * @version 1.0.0
     * @date 2024/08/02
     */
    @Data
    public static class DifyFile {
        private String         type;
        @JsonProperty("transfer_method")
        private TransferMethod transferMethod;
        private String         url;

        public enum TransferMethod {
            /**
             * 远程 URL
             */
            @JsonProperty("remote_url") REMOTE_URL,
            /**
             * 本地文件
             */
            @JsonProperty("local_file") LOCAL_FILE
        }
    }
}
