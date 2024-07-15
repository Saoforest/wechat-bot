package top.xiaolinz.wechat.bot.extend.gpt;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.ai.chat.messages.Message;
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

    private final List<Message> messages = Lists.newCopyOnWriteArrayList();

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public ChatContext addMessage(String message) {
        messages.add(new UserMessage(message));
        return this;
    }
}
