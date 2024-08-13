package top.xiaolinz.wechat.bot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.xiaolinz.wechat.bot.core.WechatBotManager;
import top.xiaolinz.wechat.bot.core.WechatClient;
import top.xiaolinz.wechat.bot.core.WechatRequestHandler;
import top.xiaolinz.wechat.bot.core.model.WechatCallBackRequest;

/**
 * 微信 bean 注入
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/13
 */
@Component
public class WechatBeanInject {

    public WechatBeanInject(WeChatBotConfig weChatBotConfig) {
        WechatBotManager.setWeChatBotConfig(weChatBotConfig);
    }

    @Autowired
    public void setWechatClient(WechatClient wechatClient) {
        WechatBotManager.setWechatClient(wechatClient);
    }

    @Autowired
    public void setCallbackWechatRequestHandler(
        WechatRequestHandler<WechatCallBackRequest> callbackWechatRequestHandler) {
        WechatBotManager.setCallbackWechatRequestHandler(callbackWechatRequestHandler);
    }
}
