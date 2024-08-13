package top.xiaolinz.wechat.bot.plugin.group.messages;

import top.xiaolinz.wechat.bot.core.WechatCallbackListener;
import top.xiaolinz.wechat.bot.core.WechatClient;
import top.xiaolinz.wechat.bot.core.enums.WechatMessageTypeEnum;
import top.xiaolinz.wechat.bot.core.model.callback.RecallMessageWechatCallback;
import top.xiaolinz.wechat.bot.plugin.group.messages.config.groupMessagesPluginProperties;

/**
 * 加入群聊聊天提醒微信消息监听
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/11
 * @see WechatCallbackListener
 */
public class JoinGroupChatReminderWechatCallbackListener
    implements WechatCallbackListener<RecallMessageWechatCallback> {

    private final WechatClient                  wechatClient;
    private final groupMessagesPluginProperties config;

    public JoinGroupChatReminderWechatCallbackListener(WechatClient wechatClient,
                                                       groupMessagesPluginProperties config) {
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
