package top.xiaolinz.wechat.bot.plugin.room.messages;

import java.util.HashMap;
import top.xiaolinz.wechat.bot.core.WechatClient;
import top.xiaolinz.wechat.bot.core.enums.MessageContentTypeEnum;
import top.xiaolinz.wechat.bot.core.enums.QueryObjTypeEnum;
import top.xiaolinz.wechat.bot.core.enums.WechatMessageTypeEnum;
import top.xiaolinz.wechat.bot.core.message.AbstractWechatMessageListener;
import top.xiaolinz.wechat.bot.core.model.dto.QueryObjResultTransfer;
import top.xiaolinz.wechat.bot.core.model.message.RecallMessageWechatMessage;
import top.xiaolinz.wechat.bot.core.model.message.RecallMessageWechatMessage.RecallData;
import top.xiaolinz.wechat.bot.plugin.room.messages.config.RoomManagementPluginProperties;
import top.xiaolinz.wechat.bot.plugin.room.messages.config.WithdrawalProperties;
import xyz.tiegangan.tools.common.core.utils.SpELUtil;

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

    private final WechatClient wechatClient;

    public WithdrawalWechatMessageListener(WechatClient wechatClient) {
        this.wechatClient = wechatClient;
    }

    @Override
    public void listener(RecallMessageWechatMessage data) {
        final RecallData             recallData = data.getData();
        final String fromWxid  = recallData.getFromWxid();
        final String finalWxid = recallData.getFinalFromWxid();
        // 查询对象信息 （缓存）
        final QueryObjResultTransfer objResultTransfer =
            wechatClient.queryObj(finalWxid, QueryObjTypeEnum.FETCH_FROM_CACHE);
        final MessageContentTypeEnum msgType    = recallData.getMsgType();
        final WithdrawalProperties    withdrawalConfig = getConfig().getWithdrawalConfig();
        final HashMap<String, Object> context          = new HashMap<>(2);
        context.put("userInfo", objResultTransfer);
        context.put("msg", recallData.getMsg());
        switch (msgType) {
            case MessageContentTypeEnum.TEXT -> {
                final String text = SpELUtil.parseSpEL(context, withdrawalConfig.getWithdrawalTextMessageTemplate());
                // 发送消息
                wechatClient.sendText(fromWxid, text);
            }
            default -> {
            }
        }
    }

    @Override
    public boolean support(WechatMessageTypeEnum type) {
        return WechatMessageTypeEnum.RECALL.equals(type);
    }
}
