package top.xiaolinz.wechat.bot.core.factory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import top.xiaolinz.wechat.bot.core.enums.WechatRequestMethodEnum;

/**
 * 微信客户要求上下文
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/8
 */
@Getter
@Setter
@Builder
public class WechatClientRequestContext {
    /**
     * 请求方法
     */
    private WechatRequestMethodEnum requestMethod;
    /**
     * 参数
     */
    private WechatRequestParams     params;
}
