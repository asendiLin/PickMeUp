package com.sendi.pickmeup.entity;

/**
 * Created by lizheng on 2018/6/8.
 */

public class VehicleOwn {
    String from_place;
    String over_time;
    String price;
    String start_time;
    String to_place;
    String passenger_name;
    String passenger_phone;

    public String getFrom_place() {
        return from_place;
    }

    public void setFrom_place(String from_place) {
        this.from_place = from_place;
    }

    public String getOver_time() {
        return over_time;
    }

    public void setOver_time(String over_time) {
        this.over_time = over_time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getTo_place() {
        return to_place;
    }

    public void setTo_place(String to_place) {
        this.to_place = to_place;
    }

    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public String getPassenger_phone() {
        return passenger_phone;
    }

    public void setPassenger_phone(String passenger_phone) {
        this.passenger_phone = passenger_phone;
    }

    @Override
    public String toString() {
        return "VehicleOwn{" +
                "from_place='" + from_place + '\'' +
                ", over_time='" + over_time + '\'' +
                ", price='" + price + '\'' +
                ", start_time='" + start_time + '\'' +
                ", to_place='" + to_place + '\'' +
                ", passenger_name='" + passenger_name + '\'' +
                ", passenger_phone='" + passenger_phone + '\'' +
                '}';
    }
}
