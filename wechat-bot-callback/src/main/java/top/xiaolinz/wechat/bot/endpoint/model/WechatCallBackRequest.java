package top.xiaolinz.wechat.bot.endpoint.model;

import lombok.Data;

/**
 * 微信回调请求
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/07/02
 */
@Data
public class WechatCallBackRequest {
    private String data;
    private String wxid;
    private long   event;
}
