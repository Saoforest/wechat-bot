package top.xiaolinz.wechat.bot.core.model.dto;

import lombok.Data;

/**
 * 调用微信 dto
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 */
@Data
public class CallWechatRequestDTO {

    /**
     * 类型
     */
    private String type;
    /**
     * 数据
     */
    private Object data;

}
