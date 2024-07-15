package top.xiaolinz.wechat.bot.core.model;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * 微信回调请求
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/07/02
 */
@Data
public class WechatCallBack {
    /**
     * 数据
     */
    private JSONObject data;
    /**
     * 微信 id，是哪个微信号发出的回调消息
     */
    private String     wxid;
    /**
     * 事件
     */
    private long       event;
}
