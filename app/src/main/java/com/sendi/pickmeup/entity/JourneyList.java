package com.sendi.pickmeup.entity;

import java.util.List;

/**
 * Created by sendi on 2018/6/7.
 */

public class JourneyList {

    List<Journey> mJourneys;

    public List<Journey> getJourneys() {
        return mJourneys;
    }

    public void setJourneys(List<Journey> journeys) {
        mJourneys = journeys;
    }

    public  class Journey {


        String journey_id;
        String driver_id;
        boolean is_finish;
        String from_place;
        String over_time;
        String price;
        String start_time;
        String to_place;
        String user_id;
        String wait_time;
        String identity;//身份识别

        public String getJourney_id() {
            return journey_id;
        }

        public void setJourney_id(String journey_id) {
            this.journey_id = journey_id;
        }

        public String getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(String driver_id) {
            this.driver_id = driver_id;
        }

        public boolean isIs_finish() {
            return is_finish;
        }

        public void setIs_finish(boolean is_finish) {
            this.is_finish = is_finish;
        }

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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getWait_time() {
            return wait_time;
        }

        public void setWait_time(String wait_time) {
            this.wait_time = wait_time;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }


        @Override
        public String toString() {
            return "Journey{" +
                    "journey_id='" + journey_id + '\'' +
                    ", driver_id='" + driver_id + '\'' +
                    ", is_finish=" + is_finish +
                    ", from_place='" + from_place + '\'' +
                    ", over_time='" + over_time + '\'' +
                    ", price='" + price + '\'' +
                    ", start_time='" + start_time + '\'' +
                    ", to_place='" + to_place + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", wait_time='" + wait_time + '\'' +
                    ", identity='" + identity + '\'' +
                    '}';
        }
    }
}