package top.xiaolinz.wechat.bot.core.client;

/**
 * wxid 持有者
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/4
 */
public class WxidHolder {
    private static final ThreadLocal<String> headerHolder = new ThreadLocal<>();

    /**
     * 获取 wxid
     *
     * @return {@link String }
     * @author huangmuhong
     * @date 2024/07/04
     */
    public static String getWxid() {
        return headerHolder.get();
    }

    /**
     * 设置 wxid
     *
     * @param wxid 編號
     * @author huangmuhong
     * @date 2024/07/04
     */
    public static void setWxid(String wxid) {
        headerHolder.set(wxid);
    }

    /**
     * 消除
     *
     * @author huangmuhong
     * @date 2024/07/04
     */
    public static void remove() {
        headerHolder.remove();
    }
}
