package top.xiaolinz.wechat.bot.core;

import com.dtflys.forest.config.ForestConfiguration;
import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.utils.TypeReference;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.dromara.hutool.core.net.url.UrlBuilder;
import org.dromara.hutool.core.net.url.UrlPath;
import org.dromara.hutool.http.client.Request;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.enums.QueryObjTypeEnum;
import top.xiaolinz.wechat.bot.core.enums.WechatRequestMethodEnum;
import top.xiaolinz.wechat.bot.core.factory.WechatClientRequestContext;
import top.xiaolinz.wechat.bot.core.factory.WechatClientRequestFactory;
import top.xiaolinz.wechat.bot.core.factory.WechatRequestParams;
import top.xiaolinz.wechat.bot.core.model.dto.QueryGroupResultTransfer;
import top.xiaolinz.wechat.bot.core.model.dto.QueryObjResultTransfer;
import top.xiaolinz.wechat.bot.core.model.dto.WechatClientRequestTransfer;
import top.xiaolinz.wechat.bot.core.model.dto.WechatClientResponseTransfer;

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
    private final        ForestConfiguration                forestClient;

    public QianXunWechatClient(
                               ForestConfiguration forestClient) {
        this.forestClient = forestClient;
    }

    @Override
    public void sendText(String wxid, String content) {
        doExecuteQianXunPost(WechatRequestMethodEnum.SEND_TEXT, Object.class, wxid, content);
    }

    @Override
    public void sendImage(String wxid, String path, String fileName) {
        doExecuteQianXunPost(WechatRequestMethodEnum.SEND_IMAGE, Object.class, wxid, path, fileName);
    }

    @Override
    public void sendCard(String wxid, String content) {
        doExecuteQianXunPost(WechatRequestMethodEnum.SEND_CARD, Object.class, wxid, content);
    }

    @Override
    public void sendReferText(String wxid, String content, String referWxid) {
        doExecuteQianXunPost(WechatRequestMethodEnum.SEND_REFER_TEXT, Object.class, referWxid, wxid, content);
    }

    @Override
    public QueryGroupResultTransfer queryGroup(String wxid, QueryObjTypeEnum type) {
        return doExecuteQianXunPost(WechatRequestMethodEnum.QUERY_GROUP, QueryGroupResultTransfer.class, wxid,
                                    type).getResult();
    }

    @Override
    public QueryObjResultTransfer queryObj(String wxid, QueryObjTypeEnum type) {
        return doExecuteQianXunPost(WechatRequestMethodEnum.QUERY_OBJ, QueryObjResultTransfer.class, wxid,
                                    type).getResult();
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
     * 执行千寻框架 post 请求
     *
     * @param type   请求类型
     * @param params 参数
     * @return {@link Request }
     * @author huangmuhong
     * @date 2024/07/14
     */
    private <T> WechatClientResponseTransfer<T> doExecuteQianXunPost(WechatRequestMethodEnum type, Class<T> resultClass,
                                                                     Object... params) {
        final WechatRequestParams requestParams = new WechatRequestParams(type).param(params);
        final WechatClientRequestContext context = WechatClientRequestContext.builder()
                                                                             .requestMethod(type)
                                                                             .params(requestParams)
                                                                             .build();
        final WechatClientRequestTransfer clientRequest = WechatClientRequestFactory.createRequest(context);
        return forestClient.post(buildRequestUrl(QIANXUN_POST_HTTP_PATH))
                           .addHeader(WXID_HEADER, WechatConfigHolder.getBindWxid())
                           .addHeader(SECRET_HEADER, WechatConfigHolder.getSecret())
                           .addBody(clientRequest)
                           .contentTypeJson()
                           .executeAsResponse()
                           .get(new TypeReference<>() {
                           });
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
