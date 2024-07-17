package top.xiaolinz.wechat.bot.core.factory;

import top.xiaolinz.wechat.bot.core.model.client.WechatClientRequest;

/**
 * 微信客户端请求工厂
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/12
 */
public interface WechatClientRequestFactory<T> {

    /**
     * 创建请求对象
     *
     *
     *
     * 入参为泛型属性顺序对应的参数
     *
     * @param params 参数
     * @return {@link WechatClientRequest }
     * @author huangmuhong
     * @date 2024/07/12
     */
    WechatClientRequest<T> createRequest(Object... params);

    /**
     * 支持
     *
     * @param type 类型
     * @return boolean
     * @author huangmuhong
     * @date 2024/07/12
     */
    boolean support(String type);
}
