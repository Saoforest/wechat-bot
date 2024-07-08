package top.xiaolinz.wechat.bot.core.event;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xiaolinz.wechat.bot.core.event.ReceiveMessageEvent.MessageData;

/**
 * 接收消息事件
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ReceiveMessageEvent extends BaseEvent<MessageData> {

    /**
     * 消息数据
     *
     * @author huangmuhong
     * @version 1.0.0
     * @date 2024/07/08
     */
    @Data
    public static class MessageData {
        private String       timeStamp;
        private Long         fromType;
        private Long         msgType;
        private Long         msgSource;
        private String       fromWxid;
        private String       finalFromWxid;
        private List<String> atWxidList;
        private Long         silence;
        private Long         membercount;
        private String       signature;
        private String       msg;
        private String       msgId;
        private String       msgBase64;
    }
}
