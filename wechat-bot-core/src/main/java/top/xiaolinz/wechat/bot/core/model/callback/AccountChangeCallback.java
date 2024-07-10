package top.xiaolinz.wechat.bot.core.model.callback;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 帐户改变回调
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 * @see Callback
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccountChangeCallback extends Callback<Object> {
    private String wxid;
    private String wxNum;
    private String nick;
    private String device;
    private String phone;
    private String avatarUrl;
    private String country;
    private String province;
    private String city;
    private String email;
    private String qq;
    private String sign;
}
