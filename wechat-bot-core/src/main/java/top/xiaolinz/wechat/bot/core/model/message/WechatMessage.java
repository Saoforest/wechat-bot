package top.xiaolinz.wechat.bot.core.model.message;

import lombok.Data;

/**
 * 微信消息
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 */
@Data
public class WechatMessage<T> {
    private String type;
    private String des;
    private Long   port;
    private String timestamp;
    private Long   pid;
    private String flag;
    private T      data;
}
