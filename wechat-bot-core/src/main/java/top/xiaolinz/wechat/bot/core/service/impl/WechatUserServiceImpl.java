package top.xiaolinz.wechat.bot.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.enums.StatusEnum;
import top.xiaolinz.wechat.bot.core.mapper.WechatUserMapper;
import top.xiaolinz.wechat.bot.core.model.entity.WechatUserEntity;
import top.xiaolinz.wechat.bot.core.service.WechatUserService;

/**
 * 微信用户服务实现
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/4
 * @see WechatUserService
 */
@RequiredArgsConstructor
@Service
public class WechatUserServiceImpl implements WechatUserService {

    private final WechatUserMapper wechatUserMapper;

    @Override
    public void accountOnline(String wxid, String nickname, String phone, String email, String avatar) {
        final WechatUserEntity entity =
            WechatUserEntity.create(wxid, nickname, avatar, phone, email, StatusEnum.ENABLE);
        wechatUserMapper.insert(entity);
    }

    @Override
    public void accountOffline(String wxid) {
        final WechatUserEntity entity = wechatUserMapper.selectOneById(wxid);
        if (entity == null) {
            return;
        }
        entity.setStatus(StatusEnum.DISABLE);
        wechatUserMapper.update(entity);
    }

    @Override
    public boolean checkStatus(String wxid) {
        final WechatUserEntity entity = wechatUserMapper.selectOneById(wxid);
        return entity != null && entity.getStatus() == StatusEnum.ENABLE;
    }
}
