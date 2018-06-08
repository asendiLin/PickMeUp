package com.sendi.pickmeup.entity;

/**
 * Created by lizheng on 2018/6/8.
 */

public class User {

    /**
     * u_id : 40283d8163de3c020163de3c33f90000
     * u_name : asendi
     * u_psw : 123
     * u_phone : null
     * u_orderState : 0
     * pay_number : null
     * warning_times : 0
     */

    private String u_id;
    private String u_name;
    private String u_psw;
    private Object u_phone;
    private int u_orderState;
    private Object pay_number;
    private int warning_times;

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_psw() {
        return u_psw;
    }

    public void setU_psw(String u_psw) {
        this.u_psw = u_psw;
    }

    public Object getU_phone() {
        return u_phone;
    }

    public void setU_phone(Object u_phone) {
        this.u_phone = u_phone;
    }

    public int getU_orderState() {
        return u_orderState;
    }

    public void setU_orderState(int u_orderState) {
        this.u_orderState = u_orderState;
    }

    public Object getPay_number() {
        return pay_number;
    }

    public void setPay_number(Object pay_number) {
        this.pay_number = pay_number;
    }

    public int getWarning_times() {
        return warning_times;
    }

    public void setWarning_times(int warning_times) {
        this.warning_times = warning_times;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id='" + u_id + '\'' +
                ", u_name='" + u_name + '\'' +
                ", u_psw='" + u_psw + '\'' +
                ", u_phone=" + u_phone +
                ", u_orderState=" + u_orderState +
                ", pay_number=" + pay_number +
                ", warning_times=" + warning_times +
                '}';
    }
}
