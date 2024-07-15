package top.xiaolinz.wechat.bot.core.factory;

/**
 * 微信客户端请求工厂提供商
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/12
 */
public interface WechatClientRequestFactoryProvider {

    /**
     * 获取微信客户端请求工厂
     *
     * @param type 类型
     * @return {@link WechatClientRequestFactory}
     * @author huangmuhong
     * @date 2024/07/12
     */
    WechatClientRequestFactory<?> getWechatClientRequestFactory(String type);

}
