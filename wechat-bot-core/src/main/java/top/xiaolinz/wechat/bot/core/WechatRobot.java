package top.xiaolinz.wechat.bot.core;

import top.xiaolinz.wechat.bot.core.enums.MessageTypeEnum;
import top.xiaolinz.wechat.bot.core.model.vo.WechatCallBack;

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
     * @param wxid    微信 id
     * @param message 信息
     * @param type    类型
     * @return boolean
     * @author huangmuhong
     * @date 2024/07/10
     */
    boolean sendMessages(String wxid, String message, MessageTypeEnum type) throws Exception;

    /**
     * 处理回调
     *
     * @param request 要求
     * @author huangmuhong
     * @date 2024/07/10
     */
    void handleCallback(WechatCallBack request);
}
