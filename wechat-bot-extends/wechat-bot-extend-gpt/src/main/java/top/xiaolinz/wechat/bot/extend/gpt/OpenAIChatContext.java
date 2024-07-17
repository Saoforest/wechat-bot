package top.xiaolinz.wechat.bot.extend.gpt;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;

/**
 * openai上下文
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/14
 * @see ChatContext
 */
public class OpenAIChatContext implements ChatContext {

    private final List<Message> messages      = Lists.newCopyOnWriteArrayList();
    private       SystemMessage systemMessage = new SystemMessage("");

    @Override
    public List<Message> getMessages() {
        // 合并系统消息和用户消息
        final List<Message> result = Lists.newArrayList();
        result.add(systemMessage);
        result.addAll(messages);
        return Collections.unmodifiableList(result);
    }

    @Override
    public ChatContext addSystemMessage(String message) {
        systemMessage = new SystemMessage(message);
        return this;
    }

    @Override
    public ChatContext addMessage(String message) {
        messages.add(new UserMessage(message));
        return this;
    }
}
