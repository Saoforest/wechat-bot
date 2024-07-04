package top.xiaolinz.wechat.bot.core.model.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import top.xiaolinz.wechat.bot.core.enums.StatusEnum;

/**
 * 微信用户实体
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/4
 */
@Table("wechat_user")
@Data
public class WechatUserEntity {

    /**
     * 微信 id
     */
    @Id(keyType = KeyType.None)
    private String wxid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 0 - 下线 1 - 上线
     */
    private StatusEnum status;

    /**
     * 创造
     *
     * @param wxid     編號
     * @param nickname 昵称
     * @param avatar   头像
     * @param phone    电话
     * @param email    电子邮件
     * @param status   地位
     * @return {@link WechatUserEntity }
     * @author huangmuhong
     * @date 2024/07/04
     */
    public static WechatUserEntity create(String wxid, String nickname, String avatar, String phone, String email,
        StatusEnum status) {
        WechatUserEntity entity = new WechatUserEntity();
        entity.setWxid(wxid);
        entity.setNickname(nickname);
        entity.setAvatar(avatar);
        entity.setPhone(phone);
        entity.setEmail(email);
        entity.setStatus(status);
        return entity;
    }
}
