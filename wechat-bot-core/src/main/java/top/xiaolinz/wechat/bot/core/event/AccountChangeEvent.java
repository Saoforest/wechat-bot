// AccountChangeEvent.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package top.xiaolinz.wechat.bot.core.event;

import java.io.Serial;
import lombok.Data;

/**
 * 帐户变更事件
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/07/04
 */
@Data
public class AccountChangeEvent extends WechatEvent {
    @Serial
    private static final long   serialVersionUID = 5598469702490198434L;
    private              String qq;
    private              String wxNum;
    private              String country;
    private              String avatarUrl;
    private              String city;
    private              String sign;
    private              String wxid;
    private              long   type;
    private              String nick;
    private              String province;
    private              long   port;
    private              String phone;
    private              String device;
    private              String email;

}

