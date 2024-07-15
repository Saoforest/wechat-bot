package top.xiaolinz.wechat.bot.core;

import org.springframework.stereotype.Component;
import top.xiaolinz.wechat.bot.core.enums.CallbackTypeEnum;
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
public class DefaultAccountChangeExtension implements CallbackListener<AccountChangeCallback> {

    @Override
    public void listen(AccountChangeCallback callback) throws Exception {
        if (callback.getType()
                    .equals("1")) {
            // 上线
            WechatUserHolder.put(callback);
        } else {
            // 下线
            WechatUserHolder.remove(callback.getWxid());
        }
    }

    @Override
    public CallbackTypeEnum supportType() {
        return CallbackTypeEnum.ACCOUNT_CHANGE;
    }

}
