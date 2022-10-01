package com.sd.xingong.vo;

public class PwdVo {
    private String id;
    private String newPassword;
    private String oldPassword;
    private String type;

    public PwdVo() {
    }

    public PwdVo(String id, String newPassword, String oldPassword, String type) {
        this.id = id;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PwdVo{" +
                "id='" + id + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
