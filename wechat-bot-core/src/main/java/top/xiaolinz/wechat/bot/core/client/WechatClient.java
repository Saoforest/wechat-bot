package top.xiaolinz.wechat.bot.core.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import top.xiaolinz.wechat.bot.core.model.dto.WechatClientRequestDTO;
import top.xiaolinz.wechat.bot.core.model.dto.WechatClientResponseDTO;

/**
 * 千寻微信客户端
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 */
@FeignClient(
    name = "WechatClient", url = "${wechat.remote.url:https://localhost:7777}",
    configuration = WechatClientConfiguration.class)
interface WechatClient {

    /**
     * 调用微信接口
     *
     * @param wxid        微信 id
     * @param callRequest 调用请求
     * @return {@link Response }
     * @author huangmuhong
     * @date 2024/07/02
     */
    @PostMapping("/qianxun/httpapi")
    WechatClientResponseDTO wechatCall(@RequestHeader("wxid") String wxid,
        @RequestBody WechatClientRequestDTO callRequest);

}
