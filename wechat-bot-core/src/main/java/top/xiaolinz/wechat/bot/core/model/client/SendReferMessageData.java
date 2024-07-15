package top.xiaolinz.wechat.bot.core.model.client;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 发送参考消息数据
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/15
 * @see SendMessageData
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SendReferMessageData extends SendMessageData {
    /**
     * 回复引用的消息 id
     */
    private String msgId;
}
