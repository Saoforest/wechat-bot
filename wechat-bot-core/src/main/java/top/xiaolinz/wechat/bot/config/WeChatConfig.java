package top.xiaolinz.wechat.bot.config;

import lombok.Data;

/**
 * 微信配置相关
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 */
@Data
public class WeChatConfig {

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
