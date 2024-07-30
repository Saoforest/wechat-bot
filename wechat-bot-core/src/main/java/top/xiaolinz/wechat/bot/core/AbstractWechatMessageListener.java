package top.xiaolinz.wechat.bot.core;

import org.dromara.hutool.core.lang.Assert;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import top.xiaolinz.wechat.bot.core.model.callback.Callback;
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
public abstract class AbstractWechatMessageListener<T extends BaseMessageListenerProperties, R extends Callback<?>>
    implements WechatMessageListener<T, R>, InitializingBean {

    @Autowired(required = false)
    private T config;

    @Override
    public T getConfig() {
        return config;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(config, "config must not be null");
    }
}
