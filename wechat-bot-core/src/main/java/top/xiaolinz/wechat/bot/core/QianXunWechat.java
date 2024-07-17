package top.xiaolinz.wechat.bot.core;

import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;
import com.alibaba.fastjson2.JSONObject;
import org.dromara.hutool.http.client.Request;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.config.WeChatProperties;
import top.xiaolinz.wechat.bot.core.factory.TypePool;
import top.xiaolinz.wechat.bot.core.factory.WechatClientRequestFactory;
import top.xiaolinz.wechat.bot.core.model.client.WechatClientRequest;

/**
 * 千寻微信
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/14
 * @see Wechat
 */
@Service
public class QianXunWechat implements Wechat {
    /**
     * 调用哪个微信号的请求头
     */
    public static final  String                             WXID_HEADER                = "wxid";
    /**
     * 千寻微信 httpapi 的密钥请求头
     */
    public static final  String                             SECRET_HEADER              = "secret";
    /**
     * 千寻http路径
     */
    private static final String                             QIANXUN_POST_HTTP_PATH     = "/qianxun/httpapi";
    /**
     * 千寻得到文件http路径
     */
    private static final String                             QIANXUN_GET_FILE_HTTP_PATH = "/file";
    private final        HTTP                               wechatClient;
    private final        String                             secret;
    private final        WechatClientRequestFactoryProvider provider;

    public QianXunWechat(HTTP wechatClient, WeChatProperties weChatProperties,
                         WechatClientRequestFactoryProvider wechatClientRequestFactoryProvider) {
        this.wechatClient = wechatClient;
        secret            = weChatProperties.getSecret();
        provider          = wechatClientRequestFactoryProvider;
    }

    @Override
    public void sendText(String wxid, String content) {
        doExecuteQianXunPost(TypePool.SEND_TEXT, wxid, content);
    }

    @Override
    public void sendImage(String wxid, String content) {
        doExecuteQianXunPost(TypePool.SEND_IMAGE, wxid, content);
    }

    @Override
    public void sendCard(String wxid, String content) {
        doExecuteQianXunPost(TypePool.SEND_CARD, wxid, content);
    }

    @Override
    public void sendReferText(String wxid, String content, String referWxid) {
        doExecuteQianXunPost(TypePool.SEND_REFER_TEXT, wxid, content, referWxid);
    }

    @Override
    public Object getGroupMessages(String wxid, String type) {
        final HttpResult result = doExecuteQianXunPost(TypePool.QUERY_GROUP, wxid, type);
        return result.getBody()
                     .toBean(JSONObject.class);
    }

    /**
     * 获取千寻文件
     *
     * @param remoteFilePath 偏僻文件路径
     * @return {@link HttpResult }
     * @author huangmuhong
     * @date 2024/07/17
     */
    private HttpResult getQianXunFile(String remoteFilePath) {
        return wechatClient.sync(QIANXUN_GET_FILE_HTTP_PATH)
                           .addUrlPara(SECRET_HEADER, secret)
                           .addUrlPara("path", remoteFilePath)
                           .get();
    }

    /**
     * 执行千寻框架 post 请求
     *
     * @param type   请求类型
     * @param params 参数
     * @return {@link Request }
     * @author huangmuhong
     * @date 2024/07/14
     */
    private HttpResult doExecuteQianXunPost(String type, Object... params) {
        final WechatClientRequestFactory<?> factory       = provider.getWechatClientRequestFactory(type);
        final WechatClientRequest<?>        clientRequest = factory.createRequest(params);
        return wechatClient.sync(QIANXUN_POST_HTTP_PATH)
                           .addHeader(WXID_HEADER, WechatHolder.getWxid())
                           .addHeader(SECRET_HEADER, secret)
                           .bodyType(OkHttps.JSON)
                           .setBodyPara(clientRequest)
                           .post();
    }
}
