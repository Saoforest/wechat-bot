package top.xiaolinz.wechat.bot.plugin.chat.config;

import static top.xiaolinz.wechat.bot.core.constants.Wechat.GLOBAL_PROPERTIES_PREFIX;
import static top.xiaolinz.wechat.bot.plugin.chat.config.ChatMessageListenerProperties.PREFIX;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.dromara.hutool.core.text.CharPool;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
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
@Component
@ConfigurationProperties(prefix = PREFIX)
@Validated
@Data
public class ChatMessageListenerProperties extends BaseMessageListenerProperties {

    public static final String PREFIX = GLOBAL_PROPERTIES_PREFIX + CharPool.DOT + "chat";

    /**
     * 上下文过期时间
     */
    // private Long contextExpire = 300L;

    /**
     * 群组个性化配置
     */
    private List<GroupChatConfig> groupChatConfig = new ArrayList<>();

    /**
     * 群组个性化配置
     *
     * @author huangmuhong
     * @version 1.0.0
     * @date 2024/07/25
     */

    @Data
    public static class GroupChatConfig {
        /**
         * 群组 wxid
         */
        @NotBlank(message = "roomId 不能为空")
        private String            roomId;
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
        private OpenAiChatOptions options      = new OpenAiChatOptions();
        /**
         * 系统提示词
         */
        private String            systemPrompt = "";
    }
}
