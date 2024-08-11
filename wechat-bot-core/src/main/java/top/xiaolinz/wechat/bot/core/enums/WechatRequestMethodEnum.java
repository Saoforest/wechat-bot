package top.xiaolinz.wechat.bot.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微信客户端请求类型池
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/12
 */
@Getter
@AllArgsConstructor
public enum WechatRequestMethodEnum {
    /**
     * 发送文本
     */
    SEND_TEXT("sendText", "发送文本", new String[] {"wxid", "msg"}),
    /**
     * 发送图片
     */
    SEND_IMAGE("sendImage", "发送图片", new String[] {"wxid", "path", "fileName"}),
    /**
     * 发送卡片
     */
    SEND_CARD("sendCard", "发送卡片", new String[] {"wxid", "msg"}),
    /**
     * 发送引用文本
     */
    SEND_REFER_TEXT("sendReferText", "发送引用文本", new String[] {"msgId", "wxid", "msg"}),
    /**
     * 查询群组消息
     */
    QUERY_GROUP("queryGroup", "获取群组消息", new String[] {"wxid", "type"}),
    /**
     * 查询对象
     */
    QUERY_OBJ("queryObj", "查询对象信息", new String[] {"wxid", "type"});

    @JsonValue
    private String   method;
    private String   desc;
    private String[] paramKeys;

}
