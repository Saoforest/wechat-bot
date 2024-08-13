package top.xiaolinz.wechat.bot.plugin.group.messages.config;

import static top.xiaolinz.wechat.bot.plugin.group.messages.config.groupMessagesPluginProperties.PREFIX;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.xiaolinz.wechat.bot.core.WechatClient;
import top.xiaolinz.wechat.bot.plugin.group.messages.WithdrawalWechatCallbackListener;

/**
 * 房间管理自动配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/8
 */
@AutoConfiguration
public class GroupMessagesPluginAutoConfiguration {

    /**
     * 群聊管理插件属性
     *
     * @return {@link groupMessagesPluginProperties }
     * @author huangmuhong
     * @date 2024/08/08
     */
    @Bean
    @ConfigurationProperties(prefix = PREFIX)
    public groupMessagesPluginProperties groupManagementPluginProperties() {
        return new groupMessagesPluginProperties();
    }

    @Bean
    public WithdrawalWechatCallbackListener withdrawalWechatMessageListener(WechatClient wechatClient,
                                                                            groupMessagesPluginProperties groupMessagesPluginProperties) {
        return new WithdrawalWechatCallbackListener(wechatClient, groupMessagesPluginProperties);
    }

}
