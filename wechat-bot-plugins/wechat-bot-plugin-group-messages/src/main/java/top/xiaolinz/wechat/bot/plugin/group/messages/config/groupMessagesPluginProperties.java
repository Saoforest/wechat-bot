package top.xiaolinz.wechat.bot.plugin.group.messages.config;

import static top.xiaolinz.wechat.bot.core.constants.WechatBot.WECHAT_BOT_PLUGIN_PREFIX;

import lombok.Data;
import org.dromara.hutool.core.text.CharPool;

/**
 * 房间管理插件属性
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/6
 */
@Data
public class groupMessagesPluginProperties {

    public static final String PREFIX = WECHAT_BOT_PLUGIN_PREFIX + CharPool.DOT + "group-management";

    /**
     * 撤回配置
     */
    private WithdrawalListenerProperties withdrawalConfig = new WithdrawalListenerProperties();
}
