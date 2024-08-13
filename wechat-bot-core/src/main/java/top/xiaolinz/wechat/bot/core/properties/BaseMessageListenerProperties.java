package top.xiaolinz.wechat.bot.core.properties;

import lombok.Data;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 基本插件属性
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/22
 */
@RefreshScope
@Data
public abstract class BaseMessageListenerProperties {

    /**
     * 是否启用监听器
     */
    private boolean enableListener = true;
}
