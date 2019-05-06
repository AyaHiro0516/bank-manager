package cn.ayahiro.manager.model;

public class FormBean {
    private String userName;
    private String passWord;

    public FormBean() {
    }

    public FormBean(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "FormBean{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormBean formBean = (FormBean) o;

        if (userName != null ? !userName.equals(formBean.userName) : formBean.userName != null) return false;
        return passWord != null ? passWord.equals(formBean.passWord) : formBean.passWord == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (passWord != null ? passWord.hashCode() : 0);
        return result;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
