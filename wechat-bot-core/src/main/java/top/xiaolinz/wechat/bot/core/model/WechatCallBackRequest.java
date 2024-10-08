package top.xiaolinz.wechat.bot.core.model;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import top.xiaolinz.wechat.bot.core.enums.WechatMessageTypeEnum;

/**
 * 微信回调参数
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/07/02
 */
@Data
public class WechatCallBackRequest {
    /**
     * 数据
     */
    private JSONObject            data;
    /**
     * 微信 id，是哪个微信号发出的回调消息
     */
    private String                wxid;
    /**
     * 事件
     */
    private WechatMessageTypeEnum event;

    /**
     * 获取数据
     *
     * @return {@link T }
     * @author huangmuhong
     * @date 2024/07/24
     */
    @SuppressWarnings("unchecked")
    public <T> T getData() {
        return (T)data.toJavaObject(event.getEventClass());
    }
}
