package cn.ayahiro.manager.model.formbean;

public class Message {
    private boolean status;
    private String info;

    public Message() {
        this.status=true;
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
