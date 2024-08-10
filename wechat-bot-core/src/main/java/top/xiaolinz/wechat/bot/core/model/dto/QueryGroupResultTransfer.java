package top.xiaolinz.wechat.bot.core.model.dto;

import lombok.Data;

/**
 * 查询组结果传输
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/08/10
 */
@Data
public class QueryGroupResultTransfer {
    private String nick;
    private long   groupMemberNum;
    private String wxid;
    private String groupManger;
}
