package top.xiaolinz.wechat.bot.core.model.callback;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xiaolinz.wechat.bot.core.model.callback.ReceiveMessageCallback.MessageData;

/**
 * 收到信息回调
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 * @see Callback
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ReceiveMessageCallback extends Callback<MessageData> {

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
