package com.sendi.pickmeup.find;

import com.sendi.pickmeup.entity.Journey;

import java.util.List;

/**
 * Created by sendi on 2018/6/7.
 */

public interface IFindFragment {
    /**
     * 展示行程列表
     */
    void showOrderList(List<Journey> journeyList);

    /**
     * 接单成功
     */
    void takeOrderSuccess();

    /**
     * 接单失败
     */
    void takeOrderFail();

}
