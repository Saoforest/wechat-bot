package top.xiaolinz.wechat.bot.plugin.room.messages;

import top.xiaolinz.wechat.bot.core.WechatCallbackListener;
import top.xiaolinz.wechat.bot.core.WechatClient;
import top.xiaolinz.wechat.bot.core.enums.WechatMessageTypeEnum;
import top.xiaolinz.wechat.bot.core.model.callback.RecallMessageWechatCallback;
import top.xiaolinz.wechat.bot.plugin.room.messages.config.RoomMessagesPluginProperties;

/**
 * 加入房间聊天提醒微信消息监听
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/11
 * @see WechatCallbackListener
 */
public class JoinRoomChatReminderWechatCallbackListener implements WechatCallbackListener<RecallMessageWechatCallback> {

    private final WechatClient wechatClient;
    private final RoomMessagesPluginProperties config;

    public JoinRoomChatReminderWechatCallbackListener(WechatClient wechatClient, RoomMessagesPluginProperties config) {
        this.wechatClient = wechatClient;
        this.config = config;
    }

    @Override
    public void listener(RecallMessageWechatCallback data) {

    }

    @Override
    public boolean support(WechatMessageTypeEnum type) {
        return WechatMessageTypeEnum.GROUP_MESSAGE.equals(type);
    }
}
