package com.sendi.pickmeup.entity;

/**
 * Created by lizheng on 2018/6/8.
 */

public class UserEntity {
    private String username;
    private String phone;
    private String user_id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
