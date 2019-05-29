package cn.ayahiro.manager.model.formbean;

import java.io.Serializable;
import java.util.HashMap;

public class BusinessBean implements Serializable{
    private static final long serialVersionUID = -865297646883643000L;
    private String amount;
    private String fromName;
    private String toName;
    private String mode;
    private HashMap<String, String> error = new HashMap<>();


    public BusinessBean() {
    }

    public BusinessBean(String amount, String fromName, String toName, String mode) {
        this.amount = amount;
        this.fromName = fromName;
        this.toName = toName;
        this.mode = mode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public HashMap<String, String> getError() {
        return error;
    }

    public void setError(HashMap<String, String> error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "BusinessBean{" +
                "amount='" + amount + '\'' +
                ", fromName='" + fromName + '\'' +
                ", toName='" + toName + '\'' +
                ", mode='" + mode + '\'' +
                '}';
    }
}
