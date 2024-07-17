package top.xiaolinz.wechat.bot.core.factory;

/**
 * 微信客户端请求类型池
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/12
 */
public interface TypePool {
    /**
     * 发送文本
     */
    String SEND_TEXT       = "sendText";
    /**
     * 发送图片
     */
    String SEND_IMAGE      = "sendImage";
    /**
     * 发送卡片
     */
    String SEND_CARD       = "sendCard";
    /**
     * 获取群组消息
     */
    String QUERY_GROUP     = "queryGroup";
    /**
     * 发送参考文本
     */
    String SEND_REFER_TEXT = "sendReferText";
    /**
     * 查询对象信息
     */
    String QUERY_OBJ       = "queryObj";

}
