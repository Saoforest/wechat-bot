package top.xiaolinz.wechat.bot.core;

/**
 * 微信
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/14
 */
public interface Wechat {

    /**
     * 发送文字信息
     *
     * @param wxid    編號
     * @param content 内容
     * @author huangmuhong
     * @date 2024/07/14
     */
    void sendText(String wxid, String content);

    /**
     * 发送图片信息
     *
     * @param wxid    編號
     * @param content 内容
     * @author huangmuhong
     * @date 2024/07/14
     */
    void sendImage(String wxid, String content);

    /**
     * 发送卡片
     *
     * @param wxid    編號
     * @param content 内容
     * @author huangmuhong
     * @date 2024/07/14
     */
    void sendCard(String wxid, String content);

    /**
     * 发送引用消息文本
     *
     * @param wxid      編號
     * @param content   内容
     * @param referWxid 参考 wxid
     * @author huangmuhong
     * @date 2024/07/15
     */
    void sendReferText(String wxid, String content, String referWxid);

    /**
     * 获取群组消息
     *
     * @param wxid 編號
     * @param type 类型
     * @return {@link Object }
     * @author huangmuhong
     * @date 2024/07/14
     */
    Object getGroupMessages(String wxid, String type);

}
