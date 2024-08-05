package top.xiaolinz.wechat.bot.plugin.chat.extension.dify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import top.xiaolinz.wechat.bot.plugin.chat.extension.dify.api.DifyApi;

/**
 * dify 聊天模型
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/2
 * @see ChatModel
 */
public class DifyChatModel implements ChatModel {
    private static final Logger logger = LoggerFactory.getLogger(DifyChatModel.class);

    private final DifyApi difyApi;

    public DifyChatModel(DifyApi difyApi) {
        this.difyApi = difyApi;
    }

    @Override
    public ChatResponse call(Prompt prompt) {
        return null;
    }

    @Override
    public ChatOptions getDefaultOptions() {
        return null;
    }
}
