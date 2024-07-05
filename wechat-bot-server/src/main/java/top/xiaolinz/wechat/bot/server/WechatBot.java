package top.xiaolinz.wechat.bot.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 微信机器人应用程序
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/1
 */
@EnableFeignClients("top.xiaolinz.wechat.bot")
@SpringBootApplication(scanBasePackages = "top.xiaolinz.wechat.bot")
@MapperScan("top.xiaolinz.wechat.bot.**.mapper")
public class WechatBot {
    public static void main(String[] args) {
        SpringApplication.run(WechatBot.class, args);
    }
}