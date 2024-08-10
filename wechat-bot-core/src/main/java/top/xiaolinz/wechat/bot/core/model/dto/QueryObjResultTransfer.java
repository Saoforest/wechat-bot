package top.xiaolinz.wechat.bot.core.model.dto;

import lombok.Data;

/**
 * 查询对象结果传输
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/08/10
 */
@Data
public class QueryObjResultTransfer {
    private String remarkBrief;
    private String wxNum;
    private String country;
    private String city;
    private String sex;
    private String sign;
    private String nickWhole;
    private String remarkWhole;
    private String remark;
    private String wxid;
    private String enWhole;
    private String nickBrief;
    private String groupManger;
    private String nick;
    private String enBrief;
    private String momentsBackgroudImgUrl;
    private String province;
    private String avatarMaxUrl;
    private long   groupMemberNum;
    private String v3;
    private String v4;
    private String avatarMinUrl;
}
