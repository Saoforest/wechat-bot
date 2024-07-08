package top.xiaolinz.wechat.bot.core.model.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import top.xiaolinz.wechat.bot.core.enums.StatusEnum;

/**
 * 微信用户
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 */
@Data
@Table("wechat_users")
public class WechatUsers {

    /**
     * 微信 id
     */
    @Id(keyType = KeyType.None)
    private String     wxid;
    /**
     * 昵称
     */
    private String     nick;
    /**
     * 头像
     */
    private String     avatar;
    /**
     * 状态
     */
    private StatusEnum status;

}
