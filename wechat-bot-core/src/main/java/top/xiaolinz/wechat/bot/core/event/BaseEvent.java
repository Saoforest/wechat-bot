package top.xiaolinz.wechat.bot.core.event;

import lombok.Data;

/**
 * 基础事件
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 */
@Data
public abstract class BaseEvent<T> {
    private Long   type;
    private String des;
    private Long   port;
    private String timestamp;
    private Long   pid;
    private String flag;
    private T      data;
}
