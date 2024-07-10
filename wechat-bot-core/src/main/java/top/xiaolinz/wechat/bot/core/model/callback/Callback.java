package top.xiaolinz.wechat.bot.core.model.callback;

import lombok.Data;

/**
 * 根据回调
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 */
@Data
public class Callback<T> {
    private String type;
    private String des;
    private Long   port;
    private String timestamp;
    private Long   pid;
    private String flag;
    private T      data;
}
