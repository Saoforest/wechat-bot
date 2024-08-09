package top.xiaolinz.wechat.bot.core.message;

import top.xiaolinz.wechat.bot.core.enums.WechatMessageTypeEnum;
import top.xiaolinz.wechat.bot.core.model.message.WechatMessage;
import top.xiaolinz.wechat.bot.core.properties.BaseMessageListenerProperties;

/**
 * 微信消息监听
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/26
 */
interface WechatMessageListener<T extends BaseMessageListenerProperties, R extends WechatMessage<?>> {

    /**
     * 监听
     *
     * @param data         数据
     * @param wechatClient 微信客户端
     * @author huangmuhong
     * @date 2024/07/26
     */
    void listener(R data);

    /**
     * 是否是支持的消息类型
     *
     * @param type 类型
     * @return boolean
     * @author huangmuhong
     * @date 2024/07/28
     */
    boolean support(WechatMessageTypeEnum type);

    /**
     * 获取配置
     *
     * @return 配置
     * @author huangmuhong
     * @date 2024/07/26
     */
    T getConfig();

}
