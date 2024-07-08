package top.xiaolinz.wechat.bot.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.xiaolinz.wechat.bot.core.event.AccountChangeEvent;
import top.xiaolinz.wechat.bot.core.event.MessageRecallEvent;
import top.xiaolinz.wechat.bot.core.event.ReceiveMessageEvent;

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
public enum EventTypeEnum {

    /**
     * 账号变动事件
     */
    ACCOUNT_CHANGE(10014, "账号变动事件", AccountChangeEvent.class),
    /**
     * 收到群聊消息事件
     */
    GROUP_MESSAGE(10008, "收到群聊消息事件", ReceiveMessageEvent.class),
    /**
     * 收到私聊消息事件
     */
    PRIVATE_MESSAGE(10009, "收到私聊消息事件", ReceiveMessageEvent.class),
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
    RECALL(10013, "撤回事件", MessageRecallEvent.class),
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
     * @return {@link EventTypeEnum }
     * @author huangmuhong
     * @date 2024/07/05
     */
    public static EventTypeEnum getEventType(long event) {
        for (EventTypeEnum value : EventTypeEnum.values()) {
            if (value.getEvent() == event) {
                return value;
            }
        }
        return null;
    }

}
