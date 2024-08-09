package top.xiaolinz.wechat.bot.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微信客户要求路径枚举
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/8
 * @see Enum
 */
@Getter
@AllArgsConstructor
public enum WechatClientRequestPathEnum {

    GET_FILE("/webwxgetmsgimg", "获取文件");

    private String path;
    private String desc;
}
