package top.xiaolinz.wechat.bot.core.enums;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import top.xiaolinz.wechat.bot.core.model.message.RecallMessageWechatMessage;
import top.xiaolinz.wechat.bot.core.model.message.ReceiveMessageWechatMessage;
import xyz.tiegangan.tools.common.response.core.exception.BusinessException;

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
public enum WechatMessageTypeEnum {

    /**
     * 账号变动事件
     */
    ACCOUNT_CHANGE(10014, "账号变动事件", Object.class),
    /**
     * 收到群聊消息事件
     */
    GROUP_MESSAGE(10008, "收到群聊消息事件", ReceiveMessageWechatMessage.class),
    /**
     * 收到私聊消息事件
     */
    PRIVATE_MESSAGE(10009, "收到私聊消息事件", ReceiveMessageWechatMessage.class),
    /**
     * 自己发出消息事件
     */
    SELF_MESSAGE(10010, "自己发出消息事件", Object.class),
    /**
     * 转账事件
     */
    TRANSFER(10006, "转账事件", Object.class),
    /**
     * 撤回事件
     */
    RECALL(10013, "撤回事件", RecallMessageWechatMessage.class),
    /**
     * 好友请求事件
     */
    FRIEND_REQUEST(10011, "好友请求事件", Object.class),
    /**
     * 支付事件
     */
    PAY(10007, "支付事件", Object.class),
    /**
     * 授权到期事件
     */
    AUTH_EXPIRE(99999, "授权到期事件", Object.class),
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
     * @return {@link WechatMessageTypeEnum }
     * @author huangmuhong
     * @date 2024/07/05
     */
    @JSONField(serialize = false)
    public static WechatMessageTypeEnum getEventType(long event) {
        for (WechatMessageTypeEnum value : WechatMessageTypeEnum.values()) {
            if (value.getEvent() == event) {
                return value;
            }
        }
        throw new BusinessException("不支持的消息监听类型");
    }

}
