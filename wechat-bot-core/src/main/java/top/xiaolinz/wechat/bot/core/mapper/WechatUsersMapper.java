package top.xiaolinz.wechat.bot.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.xiaolinz.wechat.bot.core.model.entity.WechatUsers;
import xyz.tiegangan.tools.common.mybatis.core.BaseMapperX;

/**
 * 微信用户映射器
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 */
@Mapper
public interface WechatUsersMapper extends BaseMapperX<WechatUsers> {
}
