package top.xiaolinz.wechat.bot.plugin.room.messages.config;

import static top.xiaolinz.wechat.bot.core.constants.Wechat.GLOBAL_PROPERTIES_PREFIX;

import lombok.Data;
import org.dromara.hutool.core.text.CharPool;
import top.xiaolinz.wechat.bot.core.properties.BaseMessageListenerProperties;

/**
 * 房间管理插件属性
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/6
 * @see BaseMessageListenerProperties
 */
@Data
public class RoomManagementPluginProperties extends BaseMessageListenerProperties {

    public static final String PREFIX = GLOBAL_PROPERTIES_PREFIX + CharPool.DOT + "group-management";

    /**
     * 撤回配置
     */
    private WithdrawalProperties withdrawalConfig = new WithdrawalProperties();
}
