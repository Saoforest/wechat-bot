package top.xiaolinz.wechat.bot.core.process;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import top.xiaolinz.wechat.bot.core.enums.EventType;

/**
 * 微信回调上下文
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/6
 */
@Data
public class WechatEventContext {

    /**
     * 来自 wxid
     */
    private String     fromWxid;
    /**
     * 事件类型
     */
    private EventType  type;
    /**
     * 数据
     */
    private JSONObject data;
}
