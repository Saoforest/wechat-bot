package top.xiaolinz.wechat.bot.core.callback;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import top.xiaolinz.wechat.bot.core.enums.CallbackTypeEnum;

/**
 * 微信回调上下文
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/6
 */
@Data
public class WechatCallbackContext {
    /**
     * 来自 wxid
     */
    private String           fromWxid;
    /**
     * 类型
     */
    private CallbackTypeEnum type;
    /**
     * 数据
     */
    private JSONObject       data;

}
