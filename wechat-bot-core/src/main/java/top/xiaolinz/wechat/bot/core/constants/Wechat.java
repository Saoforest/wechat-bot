package top.xiaolinz.wechat.bot.core.constants;

import org.dromara.hutool.core.text.CharPool;

/**
 * 微信
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/30
 */
public class Wechat {

    /**
     * 微信前缀
     */
    public static final String WECHAT_PREFIX = "tiegangan.wechat";

    /**
     * 微信属性前缀
     */
    public static final String WECHAT_PROPERTIES_PREFIX = WECHAT_PREFIX + CharPool.DOT + "config";

    /**
     * 全局属性前缀
     */
    public static final String GLOBAL_PROPERTIES_PREFIX = WECHAT_PREFIX + CharPool.DOT + "plugin";
}
