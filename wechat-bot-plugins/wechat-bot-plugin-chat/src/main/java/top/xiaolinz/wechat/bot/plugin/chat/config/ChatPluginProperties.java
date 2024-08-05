package top.xiaolinz.wechat.bot.plugin.chat.config;

import static top.xiaolinz.wechat.bot.core.constants.Wechat.GLOBAL_PROPERTIES_PREFIX;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.dromara.hutool.core.text.CharPool;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.validation.annotation.Validated;
import top.xiaolinz.wechat.bot.core.model.properties.BaseMessageListenerProperties;

/**
 * 聊天微信扩展配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/25
 * @see BaseMessageListenerProperties
 */
@Validated
@Data
public class ChatPluginProperties extends BaseMessageListenerProperties {

    public static final String PREFIX = GLOBAL_PROPERTIES_PREFIX + CharPool.DOT + "chat";

    /**
     * 上下文过期时间
     */
    // private Long contextExpire = 300L;
    /**
     * 群组映射配置
     */
    private List<GroupMappingConfig>      groupMappingConfig = new ArrayList<>();
    /**
     * 聊天客户端配置
     */
    private Map<String, ChatClientConfig> chatClientConfig;

    /**
     * 聊天客户端配置
     *
     * @author huangmuhong
     * @version 1.0.0
     * @date 2024/08/01
     */
    @Data
    public static class ChatClientConfig {
        /**
         * api 密钥
         */
        @NotBlank(message = "apiKey 不能为空")
        private String            apiKey;
        /**
         * 基本网址
         */
        @NotBlank(message = "baseUrl 不能为空")
        private String            baseUrl;
        /**
         * 基本配置
         */
        @NotNull
        private OpenAiChatOptions options = new OpenAiChatOptions();
    }

    /**
     * 群组个性化配置
     *
     * @author huangmuhong
     * @version 1.0.0
     * @date 2024/07/25
     */

    @Data
    public static class GroupMappingConfig {
        /**
         * 群组 wxid
         */
        @NotBlank(message = "roomId 不能为空")
        private String roomId;
        /**
         * 映射到的 chatClient
         */
        @NotBlank(message = "chatClientName 不能为空")
        private String chatClientName;
        /**
         * 系统提示词
         */
        private String prompt = "";
    }
}
