package top.xiaolinz.wechat.bot.core.service;

/**
 * 微信服务
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 */
public interface WechatService {

    /**
     * 微信上线
     *
     * @param wxid   編號
     * @param nick   缺口
     * @param avater 阿瓦特
     * @author huangmuhong
     * @date 2024/07/08
     */
    void wechatOnline(String wxid, String nick, String avater);

    /**
     * 离线
     *
     * @param wxid 編號
     * @author huangmuhong
     * @date 2024/07/08
     */
    void offline(String wxid);
}
