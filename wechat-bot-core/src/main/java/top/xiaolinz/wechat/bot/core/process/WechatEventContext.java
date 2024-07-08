package top.xiaolinz.wechat.bot.core.process;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import top.xiaolinz.wechat.bot.core.enums.EventTypeEnum;

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
    private String        fromWxid;
    /**
     * 事件类型
     */
    private EventTypeEnum type;
    /**
     * 数据
     */
    private JSONObject    data;
    /**
     * 节点支持的拓展列表
     */
    private String[]      supportExtends;
}
