package top.xiaolinz.wechat.bot.core.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.xiaolinz.wechat.bot.core.config.WechatClientConfiguration;
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
    name = "WechatClient", url = "${wechat.remote.url:http://localhost:7777}",
    configuration = WechatClientConfiguration.class, path = "/qianxun")
interface WechatClient {

    /**
     * 调用微信接口
     *
     * @param callRequest 调用请求
     * @return {@link Response }
     * @author huangmuhong
     * @date 2024/07/02
     */
    @PostMapping("/httpapi")
    WechatClientResponseDTO wechatCall(@RequestBody WechatClientRequestDTO callRequest);

}
