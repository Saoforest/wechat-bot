package top.xiaolinz.wechat.bot.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 微信机器人应用程序
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/1
 */
@SpringBootApplication(scanBasePackages = "top.xiaolinz.wechat.bot")
public class WechatBot {
    public static void main(String[] args) {
        SpringApplication.run(WechatBot.class, args);
    }
}
