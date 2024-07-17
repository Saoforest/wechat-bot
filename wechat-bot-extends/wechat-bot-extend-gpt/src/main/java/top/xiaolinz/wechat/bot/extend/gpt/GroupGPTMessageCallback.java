package top.xiaolinz.wechat.bot.extend.gpt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import top.xiaolinz.wechat.bot.core.enums.CallbackTypeEnum;
import top.xiaolinz.wechat.bot.core.model.callback.ReceiveMessageCallback;
import top.xiaolinz.wechat.bot.core.model.callback.ReceiveMessageCallback.MessageData;

/**
 * 群组 gpt 消息回调
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/7/14
 * @see AbstractGPTMessageCallback
 */
@Service
public class GroupGPTMessageCallback extends AbstractGPTMessageCallback {

    private static final String AT_REGEX = "@[^\\s]+\\s*";

    public GroupGPTMessageCallback(ChatClient.Builder chatClientBuilder) {
        super(chatClientBuilder);
    }

    @Override
    public void listen(ReceiveMessageCallback callback) throws Exception {
        final MessageData messageData = callback.getData();
        final boolean     flag        = isAtMe(messageData);
        if (!flag) {
            return;
        }
        final String fromWxid = messageData.getFinalFromWxid();
        final String roomId   = messageData.getFromWxid();

        // 去除文本中的@信息
        final String msg = removeAt(messageData.getMsg());

        doChat(roomId, fromWxid, msg, messageData.getMsgId());
    }

    /**
     * 移除@信息
     *
     * @param msg 信息
     * @return {@link String }
     * @author huangmuhong
     * @date 2024/07/15
     */
    private String removeAt(String msg) {
        // @格式为：@昵称+空格,使用正则匹配
        // 例如：@小明 你好
        // 使用 Pattern.compile() 方法编译正则表达式
        Pattern compiledPattern = Pattern.compile(AT_REGEX);

        // 使用 Matcher 对象进行匹配和替换
        Matcher matcher = compiledPattern.matcher(msg);
        return matcher.replaceAll("");
    }

    @Override
    public CallbackTypeEnum supportType() {
        return CallbackTypeEnum.GROUP_MESSAGE;
    }
}
