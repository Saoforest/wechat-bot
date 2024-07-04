// RoomMessageEvent.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package top.xiaolinz.wechat.bot.core.event;
import java.util.List;

public class RoomMessageEvent {
    private RoomMessageEventData data;

    public RoomMessageEventData getData() { return data; }
    public void setData(RoomMessageEventData value) { this.data = value; }
}

// RoomMessageEventData.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

class RoomMessageEventData {
    private String des;
    private String flag;
    private DataData data;
    private long port;
    private long pid;
    private String wxid;
    private String type;
    private String timestamp;

    public String getdes() { return des; }
    public void setdes(String value) { this.des = value; }

    public String getFlag() { return flag; }
    public void setFlag(String value) { this.flag = value; }

    public DataData getData() { return data; }
    public void setData(DataData value) { this.data = value; }

    public long getPort() { return port; }
    public void setPort(long value) { this.port = value; }

    public long getpid() { return pid; }
    public void setpid(long value) { this.pid = value; }

    public String getWxid() { return wxid; }
    public void setWxid(String value) { this.wxid = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String value) { this.timestamp = value; }
}

// DataData.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

class DataData {
    private long membercount;
    private String msg;
    private long msgType;
    private String signature;
    private String finalFromWxid;
    private String msgBase64;
    private String msgId;
    private String timeStamp;
    private long fromType;
    private String fromWxid;
    private List<String> atWxidList;
    private long silence;
    private long msgSource;

    public long getMembercount() { return membercount; }
    public void setMembercount(long value) { this.membercount = value; }

    public String getMsg() { return msg; }
    public void setMsg(String value) { this.msg = value; }

    public long getMsgType() { return msgType; }
    public void setMsgType(long value) { this.msgType = value; }

    public String getSignature() { return signature; }
    public void setSignature(String value) { this.signature = value; }

    public String getFinalFromWxid() { return finalFromWxid; }
    public void setFinalFromWxid(String value) { this.finalFromWxid = value; }

    public String getMsgBase64() { return msgBase64; }
    public void setMsgBase64(String value) { this.msgBase64 = value; }

    public String getMsgId() { return msgId; }
    public void setMsgId(String value) { this.msgId = value; }

    public String getTimeStamp() { return timeStamp; }
    public void setTimeStamp(String value) { this.timeStamp = value; }

    public long getFromType() { return fromType; }
    public void setFromType(long value) { this.fromType = value; }

    public String getFromWxid() { return fromWxid; }
    public void setFromWxid(String value) { this.fromWxid = value; }

    public List<String> getAtWxidList() { return atWxidList; }
    public void setAtWxidList(List<String> value) { this.atWxidList = value; }

    public long getSilence() { return silence; }
    public void setSilence(long value) { this.silence = value; }

    public long getMsgSource() { return msgSource; }
    public void setMsgSource(long value) { this.msgSource = value; }
}
