// WechatClientResponseDTO.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package top.xiaolinz.wechat.bot.core.model.trans;

import lombok.Data;

/**
 * 调用客户端响应
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/07/04
 */
@Data
public class WechatClientResponseTrans {
    private String msg;
    private String result;
    private long   code;
    private String flag;
    private long   port;
    private long   pid;
    private String wxid;
    private String timestamp;

}
