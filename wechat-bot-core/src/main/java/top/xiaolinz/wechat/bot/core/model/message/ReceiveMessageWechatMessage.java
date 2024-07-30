package top.xiaolinz.wechat.bot.core.model.message;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xiaolinz.wechat.bot.core.enums.MessageContentTypeEnum;
import top.xiaolinz.wechat.bot.core.model.message.ReceiveMessageWechatMessage.MessageData;

/**
 * 信息消息
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 * @see WechatMessage
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ReceiveMessageWechatMessage extends WechatMessage<MessageData> {

    /**
     * 消息数据
     *
     * @author huangmuhong
     * @version 1.0.0
     * @date 2024/07/08
     */
    @Data
    public static class MessageData {
        private String                 timeStamp;
        private Long                   fromType;
        private MessageContentTypeEnum msgType;
        private Long                   msgSource;
        private String                 fromWxid;
        private String                 finalFromWxid;
        private List<String>           atWxidList;
        private Long                   silence;
        private Long                   membercount;
        private String                 signature;
        private String                 msg;
        private String                 msgId;
        private String                 msgBase64;
    }
}
