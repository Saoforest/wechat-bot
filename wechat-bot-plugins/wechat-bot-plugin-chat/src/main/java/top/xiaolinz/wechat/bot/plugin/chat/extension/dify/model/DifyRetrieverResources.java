package top.xiaolinz.wechat.bot.plugin.chat.extension.dify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * dify 检索器资源
 *
 * @author huangmuhong
 * @version 1.0.0
 * @date 2024/8/2
 */
@Data
public class DifyRetrieverResources {
    private Long   position;
    @JsonProperty("dataset_id")
    private String datasetId;
    @JsonProperty("dataset_name")
    private String datasetName;
    @JsonProperty("document_id")
    private String documentId;
    @JsonProperty("document_name")
    private String documentName;
    @JsonProperty("segment_id")
    private String segmentId;
    private Double score;
    private String content;

}
