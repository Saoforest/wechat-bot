package top.xiaolinz.wechat.bot.plugin.chat.extension.dify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * dify 聊天完成响应元数据
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/2
 */
@Data
public class DifyChatCompletionResponseMetadata {
    private DifyUsage                    usage;
    @JsonProperty("retriever_resources")
    private List<DifyRetrieverResources> retrieverResources;
}
