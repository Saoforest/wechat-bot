package top.xiaolinz.wechat.bot.plugin.group.management.config;

import static top.xiaolinz.wechat.bot.plugin.group.management.config.RoomManagementPluginProperties.PREFIX;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 房间管理自动配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/8
 */
@AutoConfiguration
public class RoomManagementAutoConfiguration {

    /**
     * 房间管理插件属性
     *
     * @return {@link RoomManagementPluginProperties }
     * @author huangmuhong
     * @date 2024/08/08
     */
    @Bean
    @ConfigurationProperties(prefix = PREFIX)
    public RoomManagementPluginProperties roomManagementPluginProperties() {
        return new RoomManagementPluginProperties();
    }

}
