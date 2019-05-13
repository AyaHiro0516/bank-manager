package cn.ayahiro.manager.model.formbean;

import cn.ayahiro.manager.model.Account;


public class AjaxResponseBody {
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
