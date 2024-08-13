package top.xiaolinz.wechat.bot.plugin.group.messages.config;

import lombok.Data;

/**
 * 撤回属性
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/8
 */
@Data
public class WithdrawalListenerProperties {

    /**
     * 撤回文本消息模板
     */
    private String withdrawalTextMessageTemplate  = "监听到文本撤回信息，发送人：#{#userInfo.nick}，消息内容：#{#msg}";
    /**
     * 其他撤回消息模板
     */
    private String withdrawalImageMessageTemplate = "监听到图片撤回信息，发送人：#{#userInfo.nick}";

}
