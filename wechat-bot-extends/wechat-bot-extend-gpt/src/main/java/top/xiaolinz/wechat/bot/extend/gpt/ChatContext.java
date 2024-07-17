package top.xiaolinz.wechat.bot.extend.gpt;

import java.util.List;
import org.springframework.ai.chat.messages.Message;

/**
 * chat上下文
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/14
 */
public interface ChatContext {

    /**
     * 获取消息
     *
     * @return {@link List }<{@link Message }>
     * @author huangmuhong
     * @date 2024/07/14
     */
    List<Message> getMessages();

    /**
     * 添加系统消息
     *
     * @param message
     * @return {@link ChatContext }
     * @author huangmuhong
     * @date 2024/07/18
     */
    ChatContext addSystemMessage(String message);

    /**
     * 添加消息
     *
     * @param message 消息
     * @return {@link ChatContext }
     * @date 2024/07/14
     */
    ChatContext addMessage(String message);

}
