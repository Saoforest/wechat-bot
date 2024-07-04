package top.xiaolinz.wechat.bot.core.event;

import java.io.Serial;
import org.springframework.context.ApplicationEvent;

/**
 * 微信事件
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 */
public abstract class WechatEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = -5263190615624364857L;

    public WechatEvent() {
        super(new Object());
    }
}
