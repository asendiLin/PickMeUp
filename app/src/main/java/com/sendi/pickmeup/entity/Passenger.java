package com.sendi.pickmeup.entity;

import java.util.Date;

/**
 * Created by lizheng on 2018/6/8.
 */

public class Passenger {
    private String j_id;
    private String from_place;
    private String to_place;
    private Long start_time;
    private String price;
    private String status="1";
    private String msg;
    private String u_name;
    private String u_phone;
    private String pay_number;

    public String getJ_id() {
        return j_id;
    }

    public void setJ_id(String j_id) {
        this.j_id = j_id;
    }

    public String getFrom_place() {
        return from_place;
    }

    public void setFrom_place(String from_place) {
        this.from_place = from_place;
    }

    public String getTo_place() {
        return to_place;
    }

    public void setTo_place(String to_place) {
        this.to_place = to_place;
    }

//    public Date getStart_time() {
//        return start_time;
//    }
//
//    public void setStart_time(Date start_time) {
//        this.start_time = start_time;
//    }


//    public String getStart_time() {
//        return start_time;
//    }
//
//    public void setStart_time(String start_time) {
//        this.start_time = start_time;
//    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getPay_number() {
        return pay_number;
    }

    public void setPay_number(String pay_number) {
        this.pay_number = pay_number;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "j_id='" + j_id + '\'' +
                ", from_place='" + from_place + '\'' +
                ", to_place='" + to_place + '\'' +
                ", start_time=" + start_time +
                ", price='" + price + '\'' +
                ", status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", u_name='" + u_name + '\'' +
                ", u_phone='" + u_phone + '\'' +
                ", pay_number='" + pay_number + '\'' +
                '}';
    }
}
