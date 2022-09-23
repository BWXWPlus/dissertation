package com.sd.xingong.vo;

public class Login {
    private String id;
    private String passWord;

    public Login() {
    }

    public Login(String id, String passWord) {
        this.id = id;
        this.passWord = passWord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id='" + id + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
