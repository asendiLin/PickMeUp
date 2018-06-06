package com.sendi.pickmeup.base;

import com.sendi.pickmeup.config.Config;

/**
 * Created by Administrator on 2018/6/2.
 */

public class BaseEntity<T> {

    int code;
    String msg;
    T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess(){
        return code== Config.SUCCESS_CODE;
    }
}
