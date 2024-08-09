package top.xiaolinz.wechat.bot.plugin.group.management;

import top.xiaolinz.wechat.bot.core.enums.MessageContentTypeEnum;
import top.xiaolinz.wechat.bot.core.enums.WechatMessageTypeEnum;
import top.xiaolinz.wechat.bot.core.message.AbstractWechatMessageListener;
import top.xiaolinz.wechat.bot.core.model.message.RecallMessageWechatMessage;
import top.xiaolinz.wechat.bot.core.model.message.RecallMessageWechatMessage.RecallData;
import top.xiaolinz.wechat.bot.plugin.group.management.config.RoomManagementPluginProperties;

/**
 * 撤回微信消息监听
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/8
 * @see AbstractWechatMessageListener
 */
public class WithdrawalWechatMessageListener
    extends AbstractWechatMessageListener<RoomManagementPluginProperties, RecallMessageWechatMessage> {
    @Override
    public void listener(RecallMessageWechatMessage data) {
        final RecallData             recallData = data.getData();
        final MessageContentTypeEnum msgType    = recallData.getMsgType();
        switch (msgType) {
            case MessageContentTypeEnum.TEXT -> {
                final String msg = recallData.getMsg();

            }
        }
    }

    @Override
    public boolean support(WechatMessageTypeEnum type) {
        return WechatMessageTypeEnum.RECALL.equals(type);
    }
}
