package top.xiaolinz.wechat.bot.plugin.group.management;

import top.xiaolinz.wechat.bot.core.WechatClient;
import top.xiaolinz.wechat.bot.core.enums.MessageContentTypeEnum;
import top.xiaolinz.wechat.bot.core.enums.WechatMessageTypeEnum;
import top.xiaolinz.wechat.bot.core.message.AbstractWechatMessageListener;
import top.xiaolinz.wechat.bot.core.model.message.ReceiveMessageWechatMessage;
import top.xiaolinz.wechat.bot.core.model.message.ReceiveMessageWechatMessage.MessageData;
import top.xiaolinz.wechat.bot.plugin.group.management.config.RoomManagementPluginProperties;

/**
 * 抽象群聊系统消息监听器
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/6
 */
public abstract class AbstractRoomSystemMessageListener
    extends AbstractWechatMessageListener<RoomManagementPluginProperties, ReceiveMessageWechatMessage> {

    @Override
    public void listener(ReceiveMessageWechatMessage data, WechatClient wechatClient) {
        final MessageData            messageData = data.getData();
        final MessageContentTypeEnum msgType     = messageData.getMsgType();
        if (msgType != MessageContentTypeEnum.SYSTEM_MESSAGES) {
            return;
        }
    }

    @Override
    public boolean support(WechatMessageTypeEnum type) {
        return type.equals(WechatMessageTypeEnum.GROUP_MESSAGE);
    }
}
