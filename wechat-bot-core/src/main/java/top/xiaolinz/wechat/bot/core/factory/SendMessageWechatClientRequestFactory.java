package top.xiaolinz.wechat.bot.core.factory;

import org.springframework.stereotype.Component;
import top.xiaolinz.wechat.bot.core.constants.WechatRequestTypePool;
import top.xiaolinz.wechat.bot.core.model.client.SendMessageData;
import top.xiaolinz.wechat.bot.core.model.client.WechatClientRequest;

/**
 * 发送消息微信客户端请求工厂
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/12
 * @see WechatClientRequestFactory
 */
@Component
public class SendMessageWechatClientRequestFactory implements WechatClientRequestFactory<SendMessageData> {
    @Override
    public WechatClientRequest<SendMessageData> createRequest(Object... params) {
        WechatClientRequest<SendMessageData> request = new WechatClientRequest<>();
        request.setType(WechatRequestTypePool.SEND_TEXT);
        final SendMessageData messageData = new SendMessageData();
        // 从参数中获取微信id
        messageData.setWxid((String)params[0]);
        // 从参数中获取消息
        messageData.setMsg((String)params[1]);
        request.setData(messageData);

        return request;

    }

    @Override
    public boolean support(String type) {
        return type.equals(WechatRequestTypePool.SEND_TEXT) || type.equals(WechatRequestTypePool.SEND_IMAGE)
            || type.equals(WechatRequestTypePool.SEND_CARD);
    }
}
