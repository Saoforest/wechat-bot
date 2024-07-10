package top.xiaolinz.wechat.bot.core;

import top.xiaolinz.wechat.bot.core.enums.MessageTypeEnum;

/**
 * 微信机器人
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/10
 */
public interface WechatRobot {

    /**
     * 发送信息
     *
     * @param wxid    編號
     * @param message 信息
     * @param type    类型
     * @return boolean
     * @author huangmuhong
     * @date 2024/07/10
     */
    boolean sendMessages(String wxid, String message, MessageTypeEnum type) throws Exception;

}
