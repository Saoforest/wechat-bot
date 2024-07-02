package top.xiaolinz.wechat.bot.core.event;

import lombok.Data;

/**
 * 微信事件
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 */
@Data
public abstract class WechatEvent {
    /**
     * 类型
     */
    private String type;
}
