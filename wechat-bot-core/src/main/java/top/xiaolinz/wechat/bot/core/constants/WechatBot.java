package top.xiaolinz.wechat.bot.core.constants;

import org.dromara.hutool.core.text.CharPool;

/**
 * 微信机器人
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/30
 */
public class WechatBot {
    /**
     * 微信前缀
     */
    public static final String WECHAT_PREFIX            = "tiegangan.wechat";
    /**
     * 微信机器人配置前缀
     */
    public static final String WECHAT_BOT_CONFIG_PREFIX = WECHAT_PREFIX + CharPool.DOT + "config";
    /**
     * 微信机器人插件前缀
     */
    public static final String WECHAT_BOT_PLUGIN_PREFIX = WECHAT_PREFIX + CharPool.DOT + "plugin";
}
