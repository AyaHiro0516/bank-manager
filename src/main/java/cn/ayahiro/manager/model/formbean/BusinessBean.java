package cn.ayahiro.manager.model.formbean;

import cn.ayahiro.manager.service.RegisterService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

public class BusinessBean {
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
    public boolean validate() {
        boolean flag=true;
        if (this.amount == null || this.amount.trim().equals("")) {
            error.put("amount", "empty value!");
            flag=false;

        } else {
            if (!this.amount.matches("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$")) {
                error.put("amount", "value must be +number!");
                flag=false;
            }
        }
        return flag;
    }
}
