package top.xiaolinz.wechat.bot.core.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import top.xiaolinz.wechat.bot.core.model.dto.CallWechatRequestDTO;

/**
 * 千寻微信客户端
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/2
 */
@FeignClient(name = "QxWechatClient", url = "${wechat.remote.url:https://localhost:7777}")
public interface QxWechatClient {

    /**
     * 调用微信接口
     *
     * @param callRequest 调用请求
     * @return {@link Response }
     * @author huangmuhong
     * @date 2024/07/02
     */
    @PostMapping("/qianxun/httpapi")
    Response callWeChat(CallWechatRequestDTO callRequest);

}
