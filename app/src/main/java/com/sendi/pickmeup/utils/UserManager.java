package com.sendi.pickmeup.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.sendi.pickmeup.entity.User;
import com.sendi.pickmeup.entity.UserEntity;

/**
 * Created by lizheng on 2018/6/8.
 */

public class UserManager {
    private static UserManager instance;

    private UserManager(){}

    public static synchronized UserManager getInstance(){
        if(instance == null){
            instance = new UserManager();
        }
        return instance;
    }

    public void saveUserInfo(Context context,String username,String phone,String user_id){
        SharedPreferences sp = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",username);
        editor.putString("phone",phone);
        editor.putString("user_id",user_id);
        editor.commit();
    }

    public UserEntity getUserInfo(Context context){
        SharedPreferences sp = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(sp.getString("username",""));
        userEntity.setPhone(sp.getString("phone",""));
        userEntity.setUser_id(sp.getString("user_id",""));
        return userEntity;
    }
}
