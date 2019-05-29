package cn.ayahiro.manager.model.formbean;

import java.io.Serializable;
import java.util.HashMap;

public class RegisterBean implements Serializable{
    private static final long serialVersionUID = 4647451596993431969L;
    private String accountType;
    private String userName;
    private String passWord;
    private String passWord2;
    private String personId;
    private String email;
    private String address;
    private HashMap<String, String> error = new HashMap<>();

    public RegisterBean() {
    }

    public RegisterBean(String accountType, String userName, String passWord, String passWord2, String personId, String email, String address) {
        this.accountType = accountType;
        this.userName = userName;
        this.passWord = passWord;
        this.passWord2 = passWord2;
        this.personId = personId;
        this.email = email;
        this.address = address;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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

    public String getPassWord2() {
        return passWord2;
    }

    public void setPassWord2(String passWord2) {
        this.passWord2 = passWord2;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<String, String> getError() {
        return error;
    }

    public void setError(HashMap<String, String> error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "RegisterBean{" +
                "accountType='" + accountType + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", passWord2='" + passWord2 + '\'' +
                ", personId='" + personId + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
