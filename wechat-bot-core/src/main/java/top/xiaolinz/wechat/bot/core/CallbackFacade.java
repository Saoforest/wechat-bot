package top.xiaolinz.wechat.bot.core;

import top.xiaolinz.wechat.bot.core.model.vo.WechatCallBack;

/**
 * 回调门面
 *
 * 回调端点从这里进入回调处理的流程
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/1
 */
public interface CallbackFacade {

    /**
     * 处理回调
     *
     * @param request 回调请求
     * @author huangmuhong
     * @date 2024/07/02
     */
    void handle(WechatCallBack request);
}
