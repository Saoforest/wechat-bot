package top.xiaolinz.wechat.bot.core;

import java.io.InputStream;
import top.xiaolinz.wechat.bot.core.enums.QueryObjTypeEnum;

/**
 * 微信客户端
 *
 *
 *
 * 封装了调用微信接口的方法
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/14
 */
public interface WechatClient {

    /**
     * 发送文字信息
     *
     * @param wxid    微信 id
     * @param content 内容
     * @author huangmuhong
     * @date 2024/07/14
     */
    void sendText(String wxid, String content);

    /**
     * 发送图片信息
     *
     * @param wxid    微信 id
     * @param content 内容
     * @author huangmuhong
     * @date 2024/07/14
     */
    void sendImage(String wxid, String content);

    /**
     * 发送卡片
     *
     * @param wxid    微信 id
     * @param content 内容
     * @author huangmuhong
     * @date 2024/07/14
     */
    void sendCard(String wxid, String content);

    /**
     * 发送引用消息文本
     *
     * @param wxid      微信 id
     * @param content   内容
     * @param referWxid 参考 wxid
     * @author huangmuhong
     * @date 2024/07/15
     */
    void sendReferText(String wxid, String content, String referWxid);

    /**
     * 获取群组消息
     *
     * @param wxid 編號
     * @param type 类型
     * @return {@link Object }
     * @author huangmuhong
     * @date 2024/07/14
     */
    Object getGroupMessages(String wxid, QueryObjTypeEnum type);

    /**
     * 查询对象
     *
     * @param wxid 編號
     * @param type 类型
     * @return {@link Object }
     * @author huangmuhong
     * @date 2024/08/08
     */
    Object queryObj(String wxid, QueryObjTypeEnum type);

    /**
     * 获取文件
     *
     * @param remoteFilePath 远程文件路径
     * @return {@link InputStream }
     * @author huangmuhong
     * @date 2024/07/17
     */
    InputStream getFile(String remoteFilePath) throws Exception;
}
