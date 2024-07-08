package top.xiaolinz.wechat.bot.core.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xiaolinz.wechat.bot.core.event.MessageRecallEvent.RecallData;

/**
 * 消息撤回事件
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 * @see BaseEvent
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageRecallEvent extends BaseEvent<RecallData> {

    /**
     * 撤回数据
     *
     * @author huangmuhong
     * @version 1.0.0
     * @date 2024/07/08
     */
    @Data
    public static class RecallData {
        private String timeStamp;
        private Long   fromType;
        private Long   msgType;
        private Long   msgSource;
        private String fromWxid;
        private String finalFromWxid;
        private String msg;
        private String msgId;
        private String msgBase64;

    }
}
