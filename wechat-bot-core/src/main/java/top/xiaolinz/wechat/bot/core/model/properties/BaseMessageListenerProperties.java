package top.xiaolinz.wechat.bot.core.model.properties;

import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * 基本插件属性
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/22
 */
@Data
public abstract class BaseMessageListenerProperties {
    /**
     * 是否启用拓展
     */
    private boolean              enable = true;
    /**
     * 定时配置
     */
    private List<ScheduleConfig> schedule;

    /**
     * 排程配置
     *
     * @author huangmuhong
     * @version 1.0.0
     * @date 2024/07/24
     */
    @Data
    public static class ScheduleConfig {

        /**
         * cron 表达式
         */
        private String              cron;
        /**
         * 参数
         */
        private Map<String, Object> params;
    }
}
