package cn.ayahiro.manager.model.formbean;

import java.io.Serializable;

public class AllowCheckBean implements Serializable{
    private static final long serialVersionUID = 4790515758692939243L;
    private String userName;
    private int missNum;
    private boolean isAllow;
    private String role;
    private String permission;
    public AllowCheckBean() {
    }

    public AllowCheckBean(String userName, int missNum, boolean isAllow) {
        this.userName = userName;
        this.missNum = missNum;
        this.isAllow = isAllow;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMissNum() {
        return missNum;
    }

    public void setMissNum(int missNum) {
        this.missNum = missNum;
    }

    public boolean isAllow() {
        return isAllow;
    }

    public void setAllow(boolean allow) {
        isAllow = allow;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "AllowCheckBean{" +
                "userName='" + userName + '\'' +
                ", missNum=" + missNum +
                ", isAllow=" + isAllow +
                ", role='" + role + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
