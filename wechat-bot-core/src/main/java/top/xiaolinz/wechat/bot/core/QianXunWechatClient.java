package top.xiaolinz.wechat.bot.core;

import com.alibaba.fastjson2.JSONObject;
import com.dtflys.forest.config.ForestConfiguration;
import com.dtflys.forest.http.ForestResponse;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import org.dromara.hutool.core.net.url.UrlBuilder;
import org.dromara.hutool.core.net.url.UrlPath;
import org.dromara.hutool.http.client.Request;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.constants.WechatRequestTypePool;
import top.xiaolinz.wechat.bot.core.factory.WechatClientRequestFactory;
import top.xiaolinz.wechat.bot.core.factory.WechatClientRequestFactoryProvider;
import top.xiaolinz.wechat.bot.core.model.client.WechatClientRequest;

/**
 * 千寻微信
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/14
 * @see WechatClient
 */
@Service
public class QianXunWechatClient implements WechatClient {
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
    private final        WechatClientRequestFactoryProvider provider;
    private final        ForestConfiguration                forestClient;

    public QianXunWechatClient(WechatClientRequestFactoryProvider wechatClientRequestFactoryProvider,
                               ForestConfiguration forestClient) {
        provider          = wechatClientRequestFactoryProvider;
        this.forestClient = forestClient;
    }

    @Override
    public void sendText(String wxid, String content) {
        doExecuteQianXunPost(WechatRequestTypePool.SEND_TEXT, wxid, content);
    }

    @Override
    public void sendImage(String wxid, String content) {
        doExecuteQianXunPost(WechatRequestTypePool.SEND_IMAGE, wxid, content);
    }

    @Override
    public void sendCard(String wxid, String content) {
        doExecuteQianXunPost(WechatRequestTypePool.SEND_CARD, wxid, content);
    }

    @Override
    public void sendReferText(String wxid, String content, String referWxid) {
        doExecuteQianXunPost(WechatRequestTypePool.SEND_REFER_TEXT, wxid, content, referWxid);
    }

    @Override
    public Object getGroupMessages(String wxid, String type) {
        final ForestResponse<?> result = doExecuteQianXunPost(WechatRequestTypePool.QUERY_GROUP, wxid, type);
        return result.get(JSONObject.class);
    }

    @Override
    public InputStream getFile(String remoteFilePath) throws Exception {
        final ForestResponse<?> result = forestClient.get(buildRequestUrl(QIANXUN_GET_FILE_HTTP_PATH))
                                                     .addQuery(SECRET_HEADER, WechatConfigHolder.getSecret())
                                                     .addQuery("path", remoteFilePath)
                                                     .executeAsResponse();
        return result.getInputStream();
    }

    /**
     * 发送文本切片
     *
     * @param message 信息
     * @return {@link List }<{@link String }>
     * @author huangmuhong
     * @date 2024/07/31
     */
    private List<String> sendTextSlice(String message) {
        // 由于微信文本最大只支持 2048字符，如果长度超出就分段发送
        if (message.length() > 2048) {
            return List.of(message.split("(?<=\\G.{" + 2048 + "})"));
        }
        return List.of(message);
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
    private ForestResponse<?> doExecuteQianXunPost(String type, Object... params) {
        final WechatClientRequestFactory<?> factory       = provider.getWechatClientRequestFactory(type);
        final WechatClientRequest<?>        clientRequest = factory.createRequest(params);
        return forestClient.post(buildRequestUrl(QIANXUN_POST_HTTP_PATH))
                           .addHeader(WXID_HEADER, WechatConfigHolder.getBindWxid())
                           .addHeader(SECRET_HEADER, WechatConfigHolder.getSecret())
                           .addBody(clientRequest)
                           .contentTypeJson()
                           .executeAsResponse();
    }

    /**
     * 建立请求网址
     *
     * @param path 路径
     * @return {@link String }
     * @author huangmuhong
     * @date 2024/07/23
     */
    private String buildRequestUrl(String path) {
        return UrlBuilder.of(WechatConfigHolder.getHost())
                         .setPath(new UrlPath().parse(path, Charset.defaultCharset()))
                         .build();
    }
}
