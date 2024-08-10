package top.xiaolinz.wechat.bot.plugin.room.messages.config;

import lombok.Data;

/**
 * 撤回属性
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/8
 */
@Data
public class WithdrawalProperties {

    /**
     * 撤回文本消息模板
     */
    private String withdrawalTextMessageTemplate = "监听到文本类型撤回信息，发送人：【#userInfo.remark】,消息内容：【#msg】";
    /**
     * 其他撤回消息模板
     */
    private String otherWithdrawalMessageTemplate = "监听到其他类型撤回信息，发送人：【#userInfo.name】";

}
