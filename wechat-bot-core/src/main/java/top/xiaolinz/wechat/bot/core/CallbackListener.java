package top.xiaolinz.wechat.bot.core;

import com.google.common.collect.Maps;
import java.util.Map;
import top.xiaolinz.wechat.bot.core.enums.EventTypeEnum;
import top.xiaolinz.wechat.bot.core.model.callback.AccountChangeCallback;
import top.xiaolinz.wechat.bot.core.model.callback.Callback;

/**
 * 千寻框架的回调监听器
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 */
public interface CallbackListener<T extends Callback<?>> {

    /**
     * 扩展处理
     *
     * @param event 事件
     * @author huangmuhong
     * @date 2024/07/08
     */
    void process(T event) throws Exception;

    /**
     * 支持类型
     *
     * @return {@link EventTypeEnum[] }
     * @author huangmuhong
     * @date 2024/07/08
     */
    EventTypeEnum[] supportTypes();

    /**
     * 微信用户持有者
     *
     * @author huangmuhong
     * @version 1.0.0
     * @date 2024/07/09
     */
    class WechatUserHolder {

        private static final Map<String, AccountChangeCallback> userMap = Maps.newConcurrentMap();

        /**
         * 放
         *
         * @param event 事件
         * @author huangmuhong
         * @date 2024/07/09
         */
        public static void put(AccountChangeCallback event) {
            userMap.put(event.getWxid(), event);
        }

        /**
         * 得到
         *
         * @param wxid 編號
         * @return {@link AccountChangeCallback }
         * @author huangmuhong
         * @date 2024/07/09
         */
        public static AccountChangeCallback get(String wxid) {
            return userMap.get(wxid);
        }

        /**
         * 删除
         *
         * @param wxid 編號
         * @return {@link AccountChangeCallback }
         */
        public static AccountChangeCallback remove(String wxid) {
            return userMap.remove(wxid);
        }
    }
}
