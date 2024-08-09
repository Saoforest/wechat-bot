package top.xiaolinz.wechat.bot.core.factory;

import top.xiaolinz.wechat.bot.core.model.trans.WechatClientRequestTrans;

/**
 * 微信客户端请求工厂
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/12
 */
public class WechatClientRequestFactory {

    /**
     * 创建请求对象
     * @param context 上下文
     * @return {@link WechatClientRequestTrans }
     * @author huangmuhong
     * @date 2024/07/12
     */
    public static WechatClientRequestTrans createRequest(WechatClientRequestContext context) {
        return new WechatClientRequestTrans().setType(context.getRequestMethod())
                                             .setData(context.getParams()
                                                             .getParams());
    }
}
