package cn.ayahiro.manager.model.formbean;

import java.util.HashMap;

public class RegisterBean {
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

    /*用于判断表单提交过来的数据是否合法*/
    public boolean validate() {
        //用户名不能为空，并且要是3-8的字符 abcdABcd
        boolean flag=true;
        if (this.userName == null || this.userName.trim().equals("")) {
            error.put("userName", "empty value or wrong input!");
            flag=false;

        } else {
            if (!this.userName.matches("[a-zA-Z]{3,8}")) {
                error.put("userName", "empty value or wrong input!");
                flag=false;
            }
        }

        //密码不能为空，并且要是3-8的数字
        if (this.passWord == null || this.passWord.trim().equals("")) {
            error.put("passWord", "empty value or wrong input!");
            flag=false;
        } else {
            if (!this.passWord.matches("\\d{3,8}")) {
                error.put("passWord", "empty value or wrong input!");
                flag=false;
            }
        }

        //两次密码要一致
        if (this.passWord2 != null && !this.passWord2.trim().equals("")) {
            if (!this.passWord2.equals(this.passWord)) {
                error.put("passWord2", "inconsistent password!");
                flag=false;
            }
        }

        //邮箱可以为空，如果为空就必须合法
        if (this.email != null && !this.email.trim().equals("")) {
            if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
                error.put("email", "the mailbox format is illegal!");
                flag=false;
            }
        }
        return flag;
    }
}
