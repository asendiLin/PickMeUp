package com.sendi.pickmeup.listener;

import com.sendi.pickmeup.base.BaseEntity;

/**
 * Created by sendi on 2018/6/6.
 */

public interface ResultListener<T> {
    void onSuccess(BaseEntity<T> data);
    void onFail(Throwable throwable);
    void onCodeError(String msg);
}
