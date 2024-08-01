package top.xiaolinz.wechat.bot.core.message;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import top.xiaolinz.wechat.bot.core.model.message.WechatMessage;
import top.xiaolinz.wechat.bot.core.model.properties.BaseMessageListenerProperties;

/**
 * 抽象微信回调
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/26
 * @see WechatMessageListener
 * @see ApplicationContextAware
 */
@SuppressWarnings("all")
public abstract class AbstractWechatMessageListener<T extends BaseMessageListenerProperties, R extends WechatMessage<?>>
    implements WechatMessageListener<T, R>, InitializingBean {

    @Autowired
    private T config;

    @Override
    public T getConfig() {
        return config;
    }

    /**
     * 初始化
     *
     * @author huangmuhong
     * @date 2024/08/01
     */
    protected void initialization() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initialization();
    }
}
