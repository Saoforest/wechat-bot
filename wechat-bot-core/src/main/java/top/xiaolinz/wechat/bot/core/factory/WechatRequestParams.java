package top.xiaolinz.wechat.bot.core.factory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import top.xiaolinz.wechat.bot.core.enums.WechatRequestMethodEnum;

/**
 * 微信请求参数
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/9
 */
public class WechatRequestParams {

    private List<String>        keys;
    private int                 index  = 0;
    @Getter
    private Map<String, Object> params = new HashMap<>(2);

    public WechatRequestParams(WechatRequestMethodEnum requestMethodEnum) {
        keys = Arrays.asList(requestMethodEnum.getParamKeys());
    }

    public WechatRequestParams param(Object... params) {
        for (Object param : params) {
            if (index < keys.size()) {
                this.params.put(keys.get(index++), param);
            }
        }
        return this;
    }

}
