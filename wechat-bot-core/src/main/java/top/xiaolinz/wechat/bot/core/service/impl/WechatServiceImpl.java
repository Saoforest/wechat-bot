package top.xiaolinz.wechat.bot.core.service.impl;

import com.mybatisflex.core.update.UpdateChain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.enums.StatusEnum;
import top.xiaolinz.wechat.bot.core.mapper.WechatUsersMapper;
import top.xiaolinz.wechat.bot.core.model.entity.WechatUsers;
import top.xiaolinz.wechat.bot.core.service.WechatService;

/**
 * 微信服务实现
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/8
 * @see WechatService
 */
@RequiredArgsConstructor
@Service
public class WechatServiceImpl implements WechatService {

    private final WechatUsersMapper wechatUsersMapper;

    @Override
    public void wechatOnline(String wxid, String nick, String avater) {
        final WechatUsers users = new WechatUsers();
        users.setWxid(wxid);
        users.setNick(nick);
        users.setAvatar(avater);
        users.setStatus(StatusEnum.ENABLE);
        wechatUsersMapper.insert(users);
    }

    @Override
    public void offline(String wxid) {
        UpdateChain.of(WechatUsers.class).set(WechatUsers::getStatus, StatusEnum.DISABLE).where(WechatUsers::getWxid)
            .eq(wxid).update();
    }
}
