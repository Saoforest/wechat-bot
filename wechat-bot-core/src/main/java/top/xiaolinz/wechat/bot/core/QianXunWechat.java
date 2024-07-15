package top.xiaolinz.wechat.bot.core;

import com.alibaba.fastjson2.JSON;
import java.nio.charset.Charset;
import org.dromara.hutool.core.net.url.UrlBuilder;
import org.dromara.hutool.http.client.Request;
import org.dromara.hutool.http.client.body.StringBody;
import org.dromara.hutool.http.client.engine.ClientEngine;
import org.dromara.hutool.http.meta.Method;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.config.WeChatProperties;
import top.xiaolinz.wechat.bot.core.factory.TypePool;
import top.xiaolinz.wechat.bot.core.factory.WechatClientRequestFactory;
import top.xiaolinz.wechat.bot.core.factory.WechatClientRequestFactoryProvider;
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
    public static final String                             WXID_HEADER   = "wxid";
    /**
     * 千寻微信 httpapi 的密钥请求头
     */
    public static final String                             SECRET_HEADER = "secret";
    private final       ClientEngine                       clientEngine;
    private final       String                             host;
    private final       String                             secret;
    private final       WechatClientRequestFactoryProvider provider;

    public QianXunWechat(ClientEngine clientEngine, WeChatProperties weChatProperties,
                         WechatClientRequestFactoryProvider wechatClientRequestFactoryProvider) {
        this.clientEngine = clientEngine;
        host              = weChatProperties.getHost();
        secret            = weChatProperties.getSecret();
        provider          = wechatClientRequestFactoryProvider;
    }

    @Override
    public void sendText(String wxid, String content) {
        final Request request = getRequest(TypePool.SEND_TEXT, wxid, content);
        clientEngine.send(request);
    }

    @Override
    public void sendImage(String wxid, String content) {
        final Request request = getRequest(TypePool.SEND_IMAGE, wxid, content);
        clientEngine.send(request);
    }

    @Override
    public void sendCard(String wxid, String content) {
        final Request request = getRequest(TypePool.SEND_CARD, wxid, content);
        clientEngine.send(request);
    }

    @Override
    public void sendReferText(String wxid, String content, String referWxid) {
        final Request request = getRequest(TypePool.SEND_REFER_TEXT, wxid, content, referWxid);
        clientEngine.send(request);
    }

    @Override
    public Object getGroupMessages(String wxid, String type) {
        final Request request = getRequest(TypePool.QUERY_GROUP, wxid, type);
        return clientEngine.send(request);
    }

    /**
     * 获取请求 URL 生成器
     *
     * @return {@link UrlBuilder }
     * @author huangmuhong
     * @date 2024/07/14
     */
    private UrlBuilder getReqUrlBuilder() {
        return UrlBuilder.of(host)
                         .addPath("qianxun")
                         .addPath("httpapi");
    }

    /**
     * 获取请求体
     *
     * @param type
     * @param params 参数
     * @return {@link Request }
     * @author huangmuhong
     * @date 2024/07/14
     */
    private Request getRequest(String type, Object... params) {
        final WechatClientRequestFactory<?> factory       = provider.getWechatClientRequestFactory(type);
        final WechatClientRequest<?>        clientRequest = factory.createRequest(params);
        final Request request = Request.of(getReqUrlBuilder())
                                       .method(Method.POST)
                                       .header(WXID_HEADER, WxidHolder.get())
                                       .header(SECRET_HEADER, secret)
                                       .body(
                                           new StringBody(JSON.toJSONString(clientRequest), Charset.defaultCharset()));
        return request;
    }
}
