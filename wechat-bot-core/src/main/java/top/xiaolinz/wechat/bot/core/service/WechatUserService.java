package top.xiaolinz.wechat.bot.core.service;

/**
 * 微信用户服务
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/4
 */
public interface WechatUserService {

    /**
     * 账户在线
     *
     * @param wxid     編號
     * @param nickname 昵称
     * @author huangmuhong
     * @date 2024/07/04
     */
    void accountOnline(String wxid, String nickname, String phone, String email, String avatar);

    /**
     * 帐户离线
     *
     * @param wxid 編號
     * @author huangmuhong
     * @date 2024/07/04
     */
    void accountOffline(String wxid);

    /**
     * 检查状态
     *
     * @param wxid 編號
     * @return boolean
     * @author huangmuhong
     * @date 2024/07/04
     */
    boolean checkStatus(String wxid);

}
