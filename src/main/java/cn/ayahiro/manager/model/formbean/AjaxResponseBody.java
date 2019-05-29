package cn.ayahiro.manager.model.formbean;

import cn.ayahiro.manager.model.Account;

import java.io.Serializable;


public class AjaxResponseBody implements Serializable{
    private static final long serialVersionUID = 8961097734543551230L;
    private String msg;
    private Account result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Account getResult() {
        return result;
    }

    public void setResult(Account result) {
        this.result = result;
    }

}
