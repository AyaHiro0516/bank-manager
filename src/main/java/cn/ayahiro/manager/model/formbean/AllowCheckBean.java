package cn.ayahiro.manager.model.formbean;

public class AllowCheckBean {
    private String userName;
    private int missNum;
    private boolean isAllow;

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

    @Override
    public String toString() {
        return "AllowCheckBean{" +
                "userName='" + userName + '\'' +
                ", missNum=" + missNum +
                ", isAllow=" + isAllow +
                '}';
    }
}
