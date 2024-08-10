package top.xiaolinz.wechat.bot.core.model.dto;

import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;
import top.xiaolinz.wechat.bot.core.enums.WechatRequestMethodEnum;

/**
 * 微信调用客户端请求
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 */
@Accessors(chain = true)
@Data
public class WechatClientRequestTransfer {

    /**
     * 类型
     */
    private WechatRequestMethodEnum type;
    /**
     * 数据
     */
    private Map<String, Object>     data;

}
