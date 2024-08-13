package top.xiaolinz.wechat.bot.plugin.chat.config;

import static top.xiaolinz.wechat.bot.plugin.chat.config.WechatChatPluginProperties.PREFIX;

import java.util.Map;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import top.xiaolinz.wechat.bot.plugin.chat.GroupChatWechatCallbackListener;

/**
 * 聊天自动配置
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/4
 */
@AutoConfiguration
public class ChatPluginAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = PREFIX)
    public WechatChatPluginProperties chatPluginProperties() {
        return new WechatChatPluginProperties();
    }

    /**
     * 聊天客户端 bean 注册
     *
     * @return {@link ChatClientBeanRegistry }
     * @author huangmuhong
     * @date 2024/08/04
     */
    @Bean
    public ChatClientBeanRegistry chatClientBeanRegistry(WechatChatPluginProperties wechatChatPluginProperties,
                                                         ConfigurableApplicationContext applicationContext) {
        final ChatClientBeanRegistry registry =
            new ChatClientBeanRegistry(wechatChatPluginProperties.getChatClientConfig(), applicationContext);
        registry.registerChatClient();
        return registry;
    }

    /**
     * 房间聊天微信消息监听
     *
     * @param chatClientMap 聊天客户端地图
     * @return {@link GroupChatWechatCallbackListener }
     * @author huangmuhong
     * @date 2024/08/04
     */
    @DependsOn("chatClientBeanRegistry")
    @Bean
    public GroupChatWechatCallbackListener roomChatWechatMessageListener(Map<String, ChatClient> chatClientMap,
                                                                         WechatChatPluginProperties config) {
        return new GroupChatWechatCallbackListener(chatClientMap, config);
    }
}
