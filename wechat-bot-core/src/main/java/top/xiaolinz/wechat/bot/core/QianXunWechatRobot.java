package top.xiaolinz.wechat.bot.core;

import com.yomahub.liteflow.core.FlowExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.client.QianXunWechatClient;
import top.xiaolinz.wechat.bot.core.enums.MessageTypeEnum;
import top.xiaolinz.wechat.bot.core.flow.callback.WechatEventContext;
import top.xiaolinz.wechat.bot.core.model.vo.WechatCallBack;

/**
 * 基于千寻框架的微信机器人
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/10
 * @see WechatRobot
 */
@Service
@RequiredArgsConstructor
public class QianXunWechatRobot implements WechatRobot {

    private final QianXunWechatClient qianXunWechatClient;
    private final FlowExecutor        flowExecutor;

    @Override
    public boolean sendMessages(String wxid, String message, MessageTypeEnum type) throws Exception {
        return false;
    }

    @Override
    public void handleCallback(WechatCallBack request) {
        flowExecutor.execute2Future("wechatEventHandlerChain", request, WechatEventContext.class);
    }
}
