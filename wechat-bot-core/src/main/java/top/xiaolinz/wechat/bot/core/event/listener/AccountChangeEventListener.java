package top.xiaolinz.wechat.bot.core.event.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import top.xiaolinz.wechat.bot.core.event.AccountChangeEvent;
import top.xiaolinz.wechat.bot.core.service.WechatUserService;

/**
 * 帐户变更事件监听器
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/4
 * @see ApplicationListener
 */
@RequiredArgsConstructor
public class AccountChangeEventListener implements ApplicationListener<AccountChangeEvent> {

    private final WechatUserService wechatUserService;

    @Override
    public void onApplicationEvent(AccountChangeEvent event) {
        final long eventType = event.getType();
        if (eventType == 1L) {
            wechatUserService.accountOnline(event.getWxid(), event.getNick(), event.getPhone(), event.getEmail(),
                event.getAvatarUrl());
        } else if (eventType == 0L) {
            wechatUserService.accountOffline(event.getWxid());
        }
    }
}
