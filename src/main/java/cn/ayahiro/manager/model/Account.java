package cn.ayahiro.manager.model;

import cn.ayahiro.manager.exceptions.ATMException;
import cn.ayahiro.manager.utils.UserUtil;

import java.io.Serializable;

abstract public class Account implements Serializable {
    private long userId;
    private String passWord;
    private String userName;
    private String personId;
    private String email;
    private String address;
    private String accountType;
    private double balance;
    private boolean isOnline;

    public Account() {
    }

    public Account(String passWord, String userName, String personId, String email, String address, double balance) {
        try {
            this.userId = UserUtil.makeId();
            this.passWord = passWord;
            this.userName = userName;
            this.personId = personId;
            this.email = email;
            this.balance = balance;
            this.address = address;
            this.isOnline = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public void setAddress(String adress) {
        this.address = adress;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public Account setAccountType(String accountType) {
        Account updateAC = this;
        updateAC.accountType = accountType;
        return updateAC;
    }

    public boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean online) {
        isOnline = online;
    }

    final public Account deposit(double amount) {
        Account updateAC = this;
        updateAC.setBalance(this.balance + amount);
        return updateAC;
    }

    public abstract Account withdraw(double amount) throws ATMException;


    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", passWord='" + passWord + '\'' +
                ", userName='" + userName + '\'' +
                ", personId='" + personId + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (userId != account.userId) return false;
        if (Double.compare(account.balance, balance) != 0) return false;
        if (passWord != null ? !passWord.equals(account.passWord) : account.passWord != null) return false;
        if (userName != null ? !userName.equals(account.userName) : account.userName != null) return false;
        if (personId != null ? !personId.equals(account.personId) : account.personId != null) return false;
        return email != null ? email.equals(account.email) : account.email == null;
    }

}