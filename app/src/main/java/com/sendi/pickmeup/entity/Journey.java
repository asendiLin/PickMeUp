package com.sendi.pickmeup.entity;

/**
 * Created by sendi on 2018/6/10.
 */

public class Journey {

    String j_id;
    String from_place;
    boolean overTime;
    boolean finish;
    String price;
    Long start_time;
    String to_place;
    String u_id;
    String wait_time;

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

    public boolean isOverTime() {
        return overTime;
    }

    public void setOverTime(boolean overTime) {
        this.overTime = overTime;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    public String getTo_place() {
        return to_place;
    }

    public void setTo_place(String to_place) {
        this.to_place = to_place;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getWait_time() {
        return wait_time;
    }

    public void setWait_time(String wait_time) {
        this.wait_time = wait_time;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "j_id='" + j_id + '\'' +
                ", from_place='" + from_place + '\'' +
                ", overTime='" + overTime + '\'' +
                ", finish=" + finish +
                ", price='" + price + '\'' +
                ", start_time='" + start_time + '\'' +
                ", to_place='" + to_place + '\'' +
                ", u_id='" + u_id + '\'' +
                ", wait_time='" + wait_time + '\'' +
                '}';
    }
}
