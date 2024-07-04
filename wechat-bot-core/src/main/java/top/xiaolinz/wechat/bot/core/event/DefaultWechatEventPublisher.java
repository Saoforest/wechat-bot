package top.xiaolinz.wechat.bot.core.event;

import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.endpoint.WechatEventPublisher;
import top.xiaolinz.wechat.bot.endpoint.model.WechatCallBackRequest;

/**
 * 默认微信事件发布者
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 * @see WechatEventPublisher
 */
@Service
public class DefaultWechatEventPublisher implements WechatEventPublisher {
    @Override
    public void publishEvent(WechatCallBackRequest request) {

    }
}
