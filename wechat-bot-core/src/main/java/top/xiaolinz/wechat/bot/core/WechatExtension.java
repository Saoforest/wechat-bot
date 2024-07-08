package top.xiaolinz.wechat.bot.core;

import top.xiaolinz.wechat.bot.core.enums.EventTypeEnum;

/**
 * 微信扩展接口
 *
 * 通过 process 流程分发到具体的扩展实现
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 */
public interface WechatExtension {

    /**
     * 扩展处理
     *
     * @param event 事件
     * @author huangmuhong
     * @date 2024/07/08
     */
    void process(Object event) throws Exception;

    /**
     * 支持类型
     *
     * @return {@link EventTypeEnum[] }
     * @author huangmuhong
     * @date 2024/07/08
     */
    EventTypeEnum[] supportTypes();

    /**
     * 是否支持定时任务
     *
     * @return boolean
     * @author huangmuhong
     * @date 2024/07/08
     */
    boolean supportSchedule();
}
