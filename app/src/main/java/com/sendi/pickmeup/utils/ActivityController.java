package com.sendi.pickmeup.utils;


import com.sendi.pickmeup.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizheng on 2018/6/2.
 */

public class ActivityController {
    private static List<BaseActivity> mBaseActivities = new ArrayList<>();
    /**
     * 添加activity进入容器
     * @param baseActivity
     */
    public static void addActivity(BaseActivity baseActivity){
        mBaseActivities.add(baseActivity);
    }
    public static void removeActivity(BaseActivity baseActivity){
        mBaseActivities.remove(baseActivity);
    }
    public static void clearAll(){
        for(BaseActivity baseActivity : mBaseActivities){
            baseActivity.finish();
        }
        mBaseActivities.clear();
    }
}
