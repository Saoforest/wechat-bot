package top.xiaolinz.wechat.bot.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 事件类型
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/5
 * @see Enum
 */
@Getter
@AllArgsConstructor
public enum EventType {

    /**
     * 账号变动事件
     */
    ACCOUNT_CHANGE(10014, "账号变动事件"),
    /**
     * 收到群聊消息事件
     */
    GROUP_MESSAGE(10008, "收到群聊消息事件"),
    /**
     * 收到私聊消息事件
     */
    PRIVATE_MESSAGE(10009, "收到私聊消息事件"),
    /**
     * 自己发出消息事件
     */
    SELF_MESSAGE(10010, "自己发出消息事件"),
    /**
     * 转账事件
     */
    TRANSFER(10006, "转账事件"),
    /**
     * 撤回事件
     */
    RECALL(10013, "撤回事件"),
    /**
     * 好友请求事件
     */
    FRIEND_REQUEST(10011, "好友请求事件"),
    /**
     * 支付事件
     */
    PAY(10007, "支付事件"),
    /**
     * 授权到期事件
     */
    AUTH_EXPIRE(99999, "授权到期事件"),
    ;

    /**
     * 事件
     */
    private long   event;
    /**
     * 描述
     */
    private String desc;

    /**
     * 获取事件类型
     *
     * @param event 事件
     * @return {@link EventType }
     * @author huangmuhong
     * @date 2024/07/05
     */
    public static EventType getEventType(long event) {
        for (EventType value : EventType.values()) {
            if (value.getEvent() == event) {
                return value;
            }
        }
        return null;
    }

}
