package top.xiaolinz.wechat.bot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * wx 聊天配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 */
@Data
@ConfigurationProperties(prefix = WeChatProperties.PREFIX)
public class WeChatProperties {

    public static final String PREFIX = "tiegangan.wechat";

    /**
     * 微信框架 HTTP API 地址
     */
    private String host   = "http://localhost:7777";
    /**
     * wxid
     */
    private String wxid   = "";
    /**
     * 安全密钥 默认为空
     */
    private String secret = "";
}
