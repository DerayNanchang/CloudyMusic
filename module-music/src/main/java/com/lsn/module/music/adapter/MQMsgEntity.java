package com.lsn.module.music.adapter;

/**
 * @Author : lsn
 * @CreateTime : 2023/5/8 下午 03:42
 * @Description :
 */
public class MQMsgEntity {

    private String id; // uuid
    private long date; // 时间戳 发送的时间戳
    private int mqType; // 1000 - xxxxx  不同的应用
    private Object data; // 不同业务的类型,
    private String from; // 发送者Id


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getMqType() {
        return mqType;
    }

    public void setMqType(int mqType) {
        this.mqType = mqType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
