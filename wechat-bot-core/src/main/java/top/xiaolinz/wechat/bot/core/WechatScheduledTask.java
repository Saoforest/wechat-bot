package top.xiaolinz.wechat.bot.core;

/**
 * 微信计划任务
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/10
 */
public interface WechatScheduledTask {

    /**
     * 获取 需要执行任务的 微信 id
     *
     * @return {@link String }
     * @author huangmuhong
     * @date 2024/07/10
     */
    String getFromWxid();

    /**
     * 获取任务名称
     *
     * @return {@link String }
     * @author huangmuhong
     * @date 2024/07/10
     */
    String getTaskName();

    /**
     * 获取任务描述
     *
     * @return {@link String }
     * @author huangmuhong
     * @date 2024/07/10
     */
    String getTaskDescription();

    /**
     * 获取 cron
     *
     * @return {@link String }
     * @author huangmuhong
     * @date 2024/07/10
     */
    String getCron();
}
