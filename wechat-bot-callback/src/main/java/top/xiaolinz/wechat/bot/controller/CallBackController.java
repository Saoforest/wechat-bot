package top.xiaolinz.wechat.bot.controller;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 回调控制器
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/6/30
 */
@Slf4j
@RequestMapping("wechat/callback")
@RestController
public class CallBackController {

    @PostMapping
    public void callback(@RequestBody JSONObject request) {
        log.info("收到回调请求: {}", request);
    }

}
