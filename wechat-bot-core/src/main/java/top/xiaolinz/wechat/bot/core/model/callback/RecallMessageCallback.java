package top.xiaolinz.wechat.bot.core.model.callback;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.xiaolinz.wechat.bot.core.model.callback.RecallMessageCallback.RecallData;

/**
 * 信息撤回回调
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 * @see Callback
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecallMessageCallback extends Callback<RecallData> {

    /**
     * 信息类型
     *
     * @author huangmuhong
     * @version 1.0.0
     * @date 2024/07/11
     * @see Enum
     */
    @Getter
    @RequiredArgsConstructor
    public enum MsgType {

        /**
         * 文本
         */
        TEXT(1),
        /**
         * 图像
         */
        IMAGE(3),
        /**
         * 嗓音
         */
        VOICE(34),
        /**
         * 卡片
         */
        CARD(42),
        /**
         * 视频
         */
        VIDEO(43),
        /**
         * 表情符號
         */
        EMOTICON(47),
        /**
         * 地点
         */
        LOCATION(48),
        /**
         * 分享
         */
        SHARE(49),
        /**
         * 红信封
         */
        RED_ENVELOPE(2001),
        /**
         * 小程序
         */
        MINI_APP(2002),
        /**
         * 群邀请
         */
        GROUP_INVITATION(2003),
        /**
         * 系统信息
         */
        SYSTEM_MESSAGES(10000);

        @JsonValue
        private final long type;

        /**
         * 获取消息类型
         *
         * @param type 类型
         * @return {@link MsgType }
         * @author huangmuhong
         * @date 2024/07/11
         */
        @JsonCreator
        public static MsgType getMsgtype(long type) {
            for (MsgType value : values()) {
                if (value.type == type) {
                    return value;
                }
            }
            return null;
        }

    }

    /**
     * 撤回数据
     *
     * @author huangmuhong
     * @version 1.0.0
     * @date 2024/07/08
     */
    @Data
    public static class RecallData {
        private String  timeStamp;
        private Long    fromType;
        private MsgType msgType;
        private Long    msgSource;
        private String  fromWxid;
        private String  finalFromWxid;
        private String  msg;
        private String  msgId;
        private String  msgBase64;
    }
}
