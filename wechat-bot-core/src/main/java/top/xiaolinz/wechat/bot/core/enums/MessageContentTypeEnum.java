package top.xiaolinz.wechat.bot.core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.tiegangan.tools.common.response.core.exception.BusinessException;

/**
 * msg 内容类型枚举
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/31
 * @see Enum
 */
@Getter
@RequiredArgsConstructor
public enum MessageContentTypeEnum {
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
     * @return {@link MessageContentTypeEnum }
     * @author huangmuhong
     * @date 2024/07/11
     */
    @JsonCreator
    public static MessageContentTypeEnum getMsgType(long type) {
        for (MessageContentTypeEnum value : values()) {
            if (value.type == type) {
                return value;
            }
        }
        throw new BusinessException("不支持的聊天内容消息体类型");
    }
}
