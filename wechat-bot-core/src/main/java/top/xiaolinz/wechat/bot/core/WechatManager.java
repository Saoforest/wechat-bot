package top.xiaolinz.wechat.bot.core;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import top.xiaolinz.wechat.bot.config.WeChatConfig;
import top.xiaolinz.wechat.bot.core.model.WechatCallBackRequest;

/**
 * 微信经理
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/13
 */
@UtilityClass
public class WechatManager {

    @Getter
    static                  WechatRequestHandler<WechatCallBackRequest> wechatRequestHandler;
    @Getter
    private static volatile WeChatConfig                                weChatConfig;
    @Getter
    private static          WechatClient                                wechatClient;

    /**
     * 设置微信配置
     *
     * @param weChatConfig 我们聊天配置
     * @author huangmuhong
     * @date 2024/08/13
     */
    public static void setWeChatConfig(WeChatConfig weChatConfig) {
        WechatManager.weChatConfig = weChatConfig;
    }

    /**
     * 设置微信客户端
     *
     * @param wechatClient 微信客户端
     * @author huangmuhong
     * @date 2024/08/13
     */
    public void setWechatClient(WechatClient wechatClient) {
        WechatManager.wechatClient = wechatClient;
    }

    /**
     * 设置微信回调信息处理程序
     *
     * @param wechatRequestHandler 微信消息处理器
     * @author huangmuhong
     * @date 2024/08/13
     */
    public void setWechatCallbackMessageHandler(WechatRequestHandler<WechatCallBackRequest> wechatRequestHandler) {
        WechatManager.wechatRequestHandler = wechatRequestHandler;
    }

}
