package top.xiaolinz.wechat.bot.core.factory;

/**
 * 类型池
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/12
 */
public interface TypePool {

    /**
     * 发短讯
     */
    String SEND_TEXT   = "sendText";
    /**
     * 发送图片
     */
    String SEND_IMAGE  = "sendImage";
    /**
     * 发送卡片
     */
    String SEND_CARD   = "sendCard";
    /**
     * 获取群组消息
     */
    String QUERY_GROUP = "queryGroup";

    /**
     * 发送参考文本
     */
    String SEND_REFER_TEXT = "sendReferText";
}
