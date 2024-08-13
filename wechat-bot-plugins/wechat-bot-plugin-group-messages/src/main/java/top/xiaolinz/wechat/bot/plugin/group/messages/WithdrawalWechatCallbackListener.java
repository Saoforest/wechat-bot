package top.xiaolinz.wechat.bot.plugin.group.messages;

import java.util.HashMap;
import top.xiaolinz.wechat.bot.core.WechatCallbackListener;
import top.xiaolinz.wechat.bot.core.WechatClient;
import top.xiaolinz.wechat.bot.core.enums.MessageContentTypeEnum;
import top.xiaolinz.wechat.bot.core.enums.QueryDataTypeEnum;
import top.xiaolinz.wechat.bot.core.enums.WechatMessageTypeEnum;
import top.xiaolinz.wechat.bot.core.model.callback.RecallMessageWechatCallback;
import top.xiaolinz.wechat.bot.core.model.callback.RecallMessageWechatCallback.RecallData;
import top.xiaolinz.wechat.bot.core.model.dto.QueryObjResultTransfer;
import top.xiaolinz.wechat.bot.plugin.group.messages.config.WithdrawalListenerProperties;
import top.xiaolinz.wechat.bot.plugin.group.messages.config.groupMessagesPluginProperties;
import xyz.tiegangan.tools.common.core.utils.SpelUtil;

/**
 * 撤回微信消息监听
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/8
 * @see WechatCallbackListener
 */
public class WithdrawalWechatCallbackListener implements WechatCallbackListener<RecallMessageWechatCallback> {

    private final WechatClient                  wechatClient;
    private final groupMessagesPluginProperties config;

    public WithdrawalWechatCallbackListener(WechatClient wechatClient, groupMessagesPluginProperties config) {
        this.wechatClient = wechatClient;
        this.config = config;
    }

    @Override
    public void listener(RecallMessageWechatCallback data) {
        final RecallData             recallData = data.getData();
        final String fromWxid  = recallData.getFromWxid();
        final String finalWxid = recallData.getFinalFromWxid();
        // 查询对象信息 （缓存）
        final QueryObjResultTransfer objResultTransfer =
            wechatClient.queryObj(finalWxid, QueryDataTypeEnum.FETCH_FROM_CACHE);
        final MessageContentTypeEnum       msgType          = recallData.getMsgType();
        final WithdrawalListenerProperties withdrawalConfig = config.getWithdrawalConfig();
        final HashMap<String, Object>      context          = new HashMap<>(2);
        context.put("userInfo", objResultTransfer);
        context.put("msg", recallData.getMsg());
        switch (msgType) {
            case MessageContentTypeEnum.TEXT -> {
                final String text =
                    SpelUtil.parseTemplate(context, withdrawalConfig.getWithdrawalTextMessageTemplate());
                // 发送消息
                wechatClient.sendText(fromWxid, text);
            }
            case MessageContentTypeEnum.IMAGE -> {
                final String text =
                    SpelUtil.parseTemplate(context, withdrawalConfig.getWithdrawalImageMessageTemplate());
                // 发送消息
                wechatClient.sendText(fromWxid, text);
                // 解析 xml 成对象
                final String xmlContent = recallData.getMsg();
                wechatClient.sendXml(fromWxid, xmlContent);
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
