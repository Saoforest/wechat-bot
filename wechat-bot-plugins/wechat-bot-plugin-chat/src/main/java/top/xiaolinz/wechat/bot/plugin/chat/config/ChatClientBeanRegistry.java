package top.xiaolinz.wechat.bot.plugin.chat.config;

import java.util.Map;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import top.xiaolinz.wechat.bot.plugin.chat.config.ChatPluginProperties.ChatClientConfig;

/**
 * 聊天客户端 bean 注册
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/4
 */
@RequiredArgsConstructor
public class ChatClientBeanRegistry implements ResourceLoaderAware {

    private final Map<String, ChatClientConfig>  chatClientConfig;
    private final ConfigurableApplicationContext applicationContext;

    public void registerChatClient() {
        for (final Entry<String, ChatClientConfig> entry : chatClientConfig.entrySet()) {
            final String           name      = entry.getKey();
            final ChatClientConfig config    = entry.getValue();
            final OpenAiApi        openAiApi = new OpenAiApi(config.getBaseUrl(), config.getApiKey());
            final OpenAiChatModel  chatModel = new OpenAiChatModel(openAiApi, config.getOptions());
            final ChatClient chatClient = ChatClient.builder(chatModel)
                                                    .build();
            final AbstractBeanDefinition definition = BeanDefinitionBuilder.genericBeanDefinition(ChatClient.class,
                                                                                                  () -> chatClient)
                                                                           .getBeanDefinition();
            BeanDefinitionRegistry registry = (BeanDefinitionRegistry)applicationContext.getBeanFactory();
            registry.registerBeanDefinition(name, definition);
        }
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

    }
}
