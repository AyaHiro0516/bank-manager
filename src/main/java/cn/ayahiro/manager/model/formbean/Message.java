package cn.ayahiro.manager.model.formbean;

import java.io.Serializable;

public class Message implements Serializable{
    private static final long serialVersionUID = -1733838953969370848L;
    private boolean status;
    private String info;

    public Message() {
        this.status = true;
    }

    public Message(boolean status, String info) {
        this.status = status;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Message{" +
                "status=" + status +
                ", info='" + info + '\'' +
                '}';
    }

    public boolean getStatus() {
        return status;
    }

    public Message setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public Message setInfo(String info) {
        this.info = info;
        return this;
    }
}
