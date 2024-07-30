package top.xiaolinz.wechat.bot.core.factory;

import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.constants.WechatRequestTypePool;
import top.xiaolinz.wechat.bot.core.model.client.SendReferMessageData;
import top.xiaolinz.wechat.bot.core.model.client.WechatClientRequest;

/**
 * 发送推荐消息微信客户端请求工厂
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/15
 * @see WechatClientRequestFactory
 */
@Service
public class SendReferMessageWechatClientRequestFactory implements WechatClientRequestFactory<SendReferMessageData> {
    @Override
    public WechatClientRequest<SendReferMessageData> createRequest(Object... params) {
        final SendReferMessageData messageData = new SendReferMessageData();
        messageData.setWxid((String)params[0]);
        messageData.setMsg((String)params[1]);
        messageData.setMsgId((String)params[2]);
        return new WechatClientRequest<SendReferMessageData>().setType(WechatRequestTypePool.SEND_REFER_TEXT)
                                                              .setData(messageData);
    }

    @Override
    public boolean support(String type) {
        return WechatRequestTypePool.SEND_REFER_TEXT.equals(type);
    }
}
