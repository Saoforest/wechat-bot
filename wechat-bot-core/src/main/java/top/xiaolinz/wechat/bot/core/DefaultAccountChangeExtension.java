package top.xiaolinz.wechat.bot.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.xiaolinz.wechat.bot.core.enums.EventTypeEnum;
import top.xiaolinz.wechat.bot.core.model.callback.AccountChangeCallback;

/**
 * 默认帐户更改扩展
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 * @see CallbackListener
 */
@Component
@RequiredArgsConstructor
public class DefaultAccountChangeExtension implements CallbackListener<AccountChangeCallback> {

    @Override
    public void process(AccountChangeCallback event) throws Exception {
        if (event.getType()
                 .equals("1")) {
            // 上线
            WechatUserHolder.put(event);
        } else {
            // 下线
            WechatUserHolder.remove(event.getWxid());
        }
    }

    @Override
    public EventTypeEnum[] supportTypes() {
        return new EventTypeEnum[] {EventTypeEnum.ACCOUNT_CHANGE};
    }

}
