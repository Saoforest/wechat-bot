package top.xiaolinz.wechat.bot.core;

import lombok.experimental.UtilityClass;

/**
 * wxid 持有者
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/11
 */
@UtilityClass
public class WxidHolder {
    public static final ThreadLocal<String> WXID = new ThreadLocal<>();

    /**
     * 设置当前线程的 wxid
     *
     * @param wxid 編號
     * @author huangmuhong
     * @date 2024/07/10
     */
    public static void set(String wxid) {
        WXID.set(wxid);
    }

    /**
     * 获取当前线程的 wxid
     *
     * @return {@link String }
     * @author huangmuhong
     * @date 2024/07/10
     */
    public static String get() {
        return WXID.get();
    }

    /**
     * 清除
     *
     * @author huangmuhong
     * @date 2024/07/15
     */
    public static void clear() {
        WXID.remove();
    }
}
