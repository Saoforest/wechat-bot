package top.xiaolinz.wechat.bot.core.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * wx 聊天配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 */
@Component
@Data
@ConfigurationProperties(prefix = WeChatConfig.PREFIX)
public class WeChatConfig {

    public static final String PREFIX = "tieganan.wechat";

    /**
     * 千寻框架 HTTP API 地址
     */
    private String host = "http://localhost:7777";

    /**
     * 安全密钥 默认为空
     */
    private String secret = "";
}
