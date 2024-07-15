package top.xiaolinz.wechat.bot.core.model.client;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 微信调用客户端请求
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 */
@Accessors(chain = true)
@Data
public class WechatClientRequest<T> {

    /**
     * 类型
     */
    private String type;
    /**
     * 数据
     */
    private T      data;

}
