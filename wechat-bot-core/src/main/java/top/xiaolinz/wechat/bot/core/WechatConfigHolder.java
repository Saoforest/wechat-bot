package top.xiaolinz.wechat.bot.core;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

/**
 * 微信配置持有者
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/23
 */

@UtilityClass
public class WechatConfigHolder {
    /**
     * 绑定 wxid
     */
    @Getter
    @Setter
    private static String bindWxid = "";
    /**
     * 微信框架 HTTP API 地址
     */
    @Getter
    @Setter
    private static String host     = "http://localhost:7777";
    /**
     * 安全密钥
     */
    @Getter
    @Setter
    private static String secret   = "";

}
