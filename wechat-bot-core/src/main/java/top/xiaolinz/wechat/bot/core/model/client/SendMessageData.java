package top.xiaolinz.wechat.bot.core.model.client;

import lombok.Data;

/**
 * 发送消息数据
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/12
 */
@Data
public class SendMessageData {

    /**
     * 微信 id
     */
    private String wxid;
    /**
     * 消息
     */
    private String msg;

}
