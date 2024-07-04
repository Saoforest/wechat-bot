package top.xiaolinz.wechat.bot.core.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态枚举
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/07/04
 * @see Enum
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    /**
     * 启用
     */
    ENABLE(1, "启用"),
    /**
     * 禁用
     */
    DISABLE(0, "禁用");
    /**
     * 值
     */
    @EnumValue
    private final Integer code;
    /**
     * 描述
     */
    private final String  desc;
}
