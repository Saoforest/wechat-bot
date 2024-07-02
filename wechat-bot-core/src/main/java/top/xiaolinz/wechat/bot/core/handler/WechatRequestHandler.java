package top.xiaolinz.wechat.bot.core.handler;

/**
 * 微信请求处理程序
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/1
 */
public interface WechatRequestHandler {

    /**
     * 支持
     *
     * @param type
     * @return boolean
     * @author huangmuhong
     * @date 2024/07/01
     */
    boolean support(String type);

}
