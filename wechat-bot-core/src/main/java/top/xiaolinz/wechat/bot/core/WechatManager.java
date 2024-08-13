package top.xiaolinz.wechat.bot.core;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import top.xiaolinz.wechat.bot.config.WeChatBotConfig;
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

    /**
     * 微信回调请求处理程序
     */
    @Getter
    static                      WechatRequestHandler<WechatCallBackRequest> callbackWechatRequestHandler;
    /**
     * 微信聊天配置
     */
    @Getter
    private static volatile     WeChatBotConfig                             weChatBotConfig;
    /**
     * 微信客户端
     */
    @Getter
    private static          WechatClient                                wechatClient;

    /**
     * 设置微信配置
     *
     * @param weChatBotConfig 我们聊天配置
     * @author huangmuhong
     * @date 2024/08/13
     */
    public static void setWeChatBotConfig(WeChatBotConfig weChatBotConfig) {
        WechatManager.weChatBotConfig = weChatBotConfig;
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
    public void setCallbackWechatRequestHandler(WechatRequestHandler<WechatCallBackRequest> wechatRequestHandler) {
        WechatManager.callbackWechatRequestHandler = wechatRequestHandler;
    }

}
