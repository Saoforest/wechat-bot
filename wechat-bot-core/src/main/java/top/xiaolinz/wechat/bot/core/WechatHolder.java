package top.xiaolinz.wechat.bot.core;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;
import org.dromara.hutool.extra.spring.SpringUtil;

/**
 * 微信持有人
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/11
 */
@UtilityClass
public class WechatHolder {

    private static final TransmittableThreadLocal<String> WXID = new TransmittableThreadLocal<>();
    private static       Wechat                           wechat;

    /**
     * 获取当前线程的 wxid
     *
     * @return {@link String }
     * @author huangmuhong
     * @date 2024/07/10
     */
    public static String getWxid() {
        return WXID.get();
    }

    /**
     * 设置当前线程的 wxid
     *
     * @param wxid 編號
     * @author huangmuhong
     * @date 2024/07/10
     */
    public static void setWxid(String wxid) {
        WXID.set(wxid);
    }

    /**
     * 清除当前线程的 wxid
     *
     * @author huangmuhong
     * @date 2024/07/15
     */
    public static void clearWxid() {
        WXID.remove();
    }

    /**
     * 获取微信
     *
     * @return {@link Wechat }
     * @author huangmuhong
     * @date 2024/07/17
     */
    public static Wechat getWechat() {
        if (wechat == null) {
            synchronized (WechatHolder.class) {
                if (wechat == null) {
                    wechat = SpringUtil.getBean(Wechat.class);
                }
            }
        }
        return wechat;
    }
}
