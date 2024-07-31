package top.xiaolinz.wechat.bot.core.model.properties;

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
     * 是否启用拓展
     */
    private boolean enable = true;
}
