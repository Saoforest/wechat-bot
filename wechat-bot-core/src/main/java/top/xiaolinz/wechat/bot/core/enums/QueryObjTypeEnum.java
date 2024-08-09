package top.xiaolinz.wechat.bot.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 查询对象类型枚举
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/8
 * @see Enum
 */
@Getter
@AllArgsConstructor
public enum QueryObjTypeEnum {

    /**
     * 从缓存中获取
     */
    FETCH_FROM_CACHE("1", "从缓存获取"),
    /**
     * 从内存中获取
     */
    FETCH_FROM_MEMORY("2", "从内存获取");

    /**
     * 类型
     */
    @JsonValue
    private String type;
    /**
     * 描述
     */
    private String desc;

}
