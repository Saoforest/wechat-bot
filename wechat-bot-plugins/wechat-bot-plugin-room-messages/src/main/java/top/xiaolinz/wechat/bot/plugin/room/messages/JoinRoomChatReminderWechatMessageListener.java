package top.xiaolinz.wechat.bot.plugin.room.messages;

import top.xiaolinz.wechat.bot.core.WechatClient;
import top.xiaolinz.wechat.bot.core.enums.WechatMessageTypeEnum;
import top.xiaolinz.wechat.bot.core.message.AbstractWechatMessageListener;
import top.xiaolinz.wechat.bot.core.model.message.RecallMessageWechatMessage;
import top.xiaolinz.wechat.bot.plugin.room.messages.config.RoomMessagesPluginProperties;

/**
 * 加入房间聊天提醒微信消息监听
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/11
 * @see AbstractWechatMessageListener
 */
public class JoinRoomChatReminderWechatMessageListener
    extends AbstractWechatMessageListener<RoomMessagesPluginProperties, RecallMessageWechatMessage> {

    private final WechatClient wechatClient;

    public JoinRoomChatReminderWechatMessageListener(WechatClient wechatClient) {
        this.wechatClient = wechatClient;
    }

    @Override
    public void listener(RecallMessageWechatMessage data) {

    }

    @Override
    public boolean support(WechatMessageTypeEnum type) {
        return WechatMessageTypeEnum.GROUP_MESSAGE.equals(type);
    }
}
