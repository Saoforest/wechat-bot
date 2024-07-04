package top.xiaolinz.wechat.bot.core.client;

import org.dromara.hutool.extra.spring.SpringUtil;

/**
 * 微信客户端功能
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/3
 */
public class WechatClientFunctions {

    /**
     * 获取微信客户端
     *
     * @return {@link WechatClient }
     * @author huangmuhong
     * @date 2024/07/03
     */
    private static WechatClient getWechatClient() {
        return SpringUtil.getBean(WechatClient.class);
    }
}

