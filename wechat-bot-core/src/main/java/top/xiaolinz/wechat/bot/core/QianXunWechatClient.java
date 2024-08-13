package top.xiaolinz.wechat.bot.core;

import com.dtflys.forest.config.ForestConfiguration;
import com.dtflys.forest.http.ForestResponse;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.dromara.hutool.core.net.url.UrlBuilder;
import org.dromara.hutool.core.net.url.UrlPath;
import org.dromara.hutool.http.client.Request;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.enums.QueryDataTypeEnum;
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
    private static final String                             WXID_HEADER                = "wxid";
    /**
     * 千寻微信 httpapi 的密钥请求头
     */
    private static final String                             SECRET_HEADER              = "secret";
    /**
     * 千寻http路径
     */
    private static final String                             QIANXUN_POST_HTTP_PATH     = "/qianxun/httpapi";
    /**
     * 千寻获取文件http路径
     */
    private static final String                             QIANXUN_GET_FILE_HTTP_PATH = "/file";
    private final        ForestConfiguration                forestClient;

    public QianXunWechatClient(
                               ForestConfiguration forestClient) {
        this.forestClient = forestClient;
    }

    @Override
    public void sendText(String wxid, String content) {
        doExecuteQianXunPost(WechatRequestMethodEnum.SEND_TEXT, wxid, content);
    }

    @Override
    public void sendImage(String wxid, String path, String fileName) {
        doExecuteQianXunPost(WechatRequestMethodEnum.SEND_IMAGE, wxid, path, fileName);
    }

    @Override
    public void sendCard(String wxid, String content) {
        doExecuteQianXunPost(WechatRequestMethodEnum.SEND_CARD, wxid, content);
    }

    @Override
    public void sendReferText(String wxid, String content, String referWxid) {
        doExecuteQianXunPost(WechatRequestMethodEnum.SEND_REFER_TEXT, referWxid, wxid, content);
    }

    @Override
    public void sendXml(String wxid, String content) {
        doExecuteQianXunPost(WechatRequestMethodEnum.SEND_XML, wxid, content);
    }

    @Override
    public QueryGroupResultTransfer queryGroup(String wxid, QueryDataTypeEnum type) {
        return doExecuteQianXunPost(WechatRequestMethodEnum.QUERY_GROUP, wxid, type).getResult(
            QueryGroupResultTransfer.class);
    }

    @Override
    public QueryObjResultTransfer queryObj(String wxid, QueryDataTypeEnum type) {
        return doExecuteQianXunPost(WechatRequestMethodEnum.QUERY_OBJ, wxid, type).getResult(
            QueryObjResultTransfer.class);
    }

    @Override
    public InputStream getFile(String remoteFilePath) throws Exception {
        final ForestResponse<?> result = forestClient.get(buildRequestUrl(QIANXUN_GET_FILE_HTTP_PATH))
                                                     .addQuery(SECRET_HEADER, WechatBotManager.getWeChatBotConfig()
                                                                                              .getSecret())
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
    private WechatClientResponseTransfer doExecuteQianXunPost(WechatRequestMethodEnum type,
                                                                     Object... params) {
        final WechatRequestParams requestParams = new WechatRequestParams(type).params(params);
        final WechatClientRequestContext context = WechatClientRequestContext.builder()
                                                                             .requestMethod(type)
                                                                             .params(requestParams)
                                                                             .build();
        final WechatClientRequestTransfer clientRequest = WechatClientRequestFactory.createRequest(context);

        return forestClient.post(buildRequestUrl(QIANXUN_POST_HTTP_PATH))
                           .addHeader(WXID_HEADER, WechatBotManager.getWeChatBotConfig()
                                                                   .getWxid())
                           .addHeader(SECRET_HEADER, WechatBotManager.getWeChatBotConfig()
                                                                     .getSecret())
                           .addBody(clientRequest)
                           .contentTypeJson()
                           .execute(WechatClientResponseTransfer.class);
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
        return UrlBuilder.of(WechatBotManager.getWeChatBotConfig()
                                             .getHost())
                         .setPath(new UrlPath().parse(path, Charset.defaultCharset()))
                         .build();
    }

}

