package top.xiaolinz.wechat.bot.plugin.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;

/**
 * 自定义内存聊天内存
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/30
 * @see ChatMemory
 */
public class CustomInMemoryChatMemory implements ChatMemory {
    private final Map<String, List<Message>> conversationHistory = new ConcurrentHashMap<>();
    private final int                        maxContextSize;

    public CustomInMemoryChatMemory(int maxContextSize) {
        this.maxContextSize = maxContextSize;
    }

    @Override
    public void add(String conversationId, List<Message> messages) {
        if (conversationHistory.size() >= maxContextSize) {
            clear(conversationId);
        }
        conversationHistory.putIfAbsent(conversationId, new ArrayList<>());
        conversationHistory.get(conversationId)
                           .addAll(messages);
    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        List<Message> all = conversationHistory.get(conversationId);
        return all != null ? all.stream()
                                .skip(Math.max(0, all.size() - lastN))
                                .toList() : List.of();
    }

    @Override
    public void clear(String conversationId) {
        conversationHistory.remove(conversationId);
    }
}
