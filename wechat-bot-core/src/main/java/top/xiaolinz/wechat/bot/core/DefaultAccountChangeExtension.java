package top.xiaolinz.wechat.bot.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.xiaolinz.wechat.bot.core.enums.EventTypeEnum;
import top.xiaolinz.wechat.bot.core.event.AccountChangeEvent;
import top.xiaolinz.wechat.bot.core.service.WechatService;

/**
 * 默认帐户更改扩展
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 * @see WechatExtension
 */
@Component
@RequiredArgsConstructor
public class DefaultAccountChangeExtension implements WechatExtension {

    private final WechatService wechatService;

    @Override
    public void process(Object event) throws Exception {
        final AccountChangeEvent changeEvent = (AccountChangeEvent)event;
        if (changeEvent.getType() == 1) {
            // 上线
            wechatService.wechatOnline(changeEvent.getWxid(), changeEvent.getNick(), changeEvent.getAvatarUrl());
        } else {
            // 离线
            wechatService.offline(changeEvent.getWxid());
        }
    }

    @Override
    public EventTypeEnum[] supportTypes() {
        return new EventTypeEnum[] {EventTypeEnum.ACCOUNT_CHANGE};
    }

    @Override
    public boolean supportSchedule() {
        return false;
    }
}
