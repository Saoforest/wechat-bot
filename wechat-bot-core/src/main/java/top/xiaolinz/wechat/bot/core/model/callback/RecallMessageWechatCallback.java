package top.xiaolinz.wechat.bot.core.model.callback;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.xiaolinz.wechat.bot.core.enums.MessageContentTypeEnum;
import top.xiaolinz.wechat.bot.core.model.callback.RecallMessageWechatCallback.RecallData;

/**
 * 撤回消息
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 * @see WechatCallback
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecallMessageWechatCallback extends WechatCallback<RecallData> {

    /**
     * 撤回数据
     *
     * @author huangmuhong
     * @version 1.0.0
     * @date 2024/07/08
     */
    @Data
    public static class RecallData {
        private String                 timeStamp;
        private Long                   fromType;
        private MessageContentTypeEnum msgType;
        private Long                   msgSource;
        private String                 fromWxid;
        private String                 finalFromWxid;
        private String                 msg;
        private String                 msgId;
        private String                 msgBase64;
    }
}
