package top.xiaolinz.wechat.bot.core.enums;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import top.xiaolinz.wechat.bot.core.model.callback.AccountChangeCallback;
import top.xiaolinz.wechat.bot.core.model.callback.RecallMessageCallback;
import top.xiaolinz.wechat.bot.core.model.callback.ReceiveMessageCallback;

/**
 * 回调类型
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/5
 * @see Enum
 */
@Getter
@AllArgsConstructor
public enum MessageTypeEnum {

    /**
     * 账号变动事件
     */
    ACCOUNT_CHANGE(10014, "账号变动事件", AccountChangeCallback.class),
    /**
     * 收到群聊消息事件
     */
    GROUP_MESSAGE(10008, "收到群聊消息事件", ReceiveMessageCallback.class),
    /**
     * 收到私聊消息事件
     */
    PRIVATE_MESSAGE(10009, "收到私聊消息事件", ReceiveMessageCallback.class),
    /**
     * 自己发出消息事件
     */
    // SELF_MESSAGE(10010, "自己发出消息事件", "selfMessageHandler"),
    /**
     * 转账事件
     */
    // TRANSFER(10006, "转账事件", "transferHandler"),
    /**
     * 撤回事件
     */
    RECALL(10013, "撤回事件", RecallMessageCallback.class),
    /**
     * 好友请求事件
     */
    // FRIEND_REQUEST(10011, "好友请求事件", "friendRequestHandler"),
    /**
     * 支付事件
     */
    // PAY(10007, "支付事件", "payHandler"),
    /**
     * 授权到期事件
     */
    // AUTH_EXPIRE(99999, "授权到期事件", "authExpireHandler"),
    ;

    /**
     * 事件
     */
    @JSONField
    private final long     event;
    /**
     * 描述
     */
    private final String   desc;
    /**
     * 节点名称
     */
    private final Class<?> eventClass;

    /**
     * 获取事件类型
     *
     * @param event 事件
     * @return {@link MessageTypeEnum }
     * @author huangmuhong
     * @date 2024/07/05
     */
    @JSONField(serialize = false)
    public static MessageTypeEnum getEventType(long event) {
        for (MessageTypeEnum value : MessageTypeEnum.values()) {
            if (value.getEvent() == event) {
                return value;
            }
        }
        return null;
    }

}
