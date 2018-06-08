package com.sendi.pickmeup.network;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.sendi.pickmeup.base.BaseEntity;
import com.sendi.pickmeup.entity.User;
import com.sendi.pickmeup.listener.ResultListener;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by asendi on 2018/6/6.
 */

public class Netowrk{
    private static OkHttpClient mOkHttpClient = initOkHttpClient();

    private static OkHttpClient initOkHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.writeTimeout(5, TimeUnit.SECONDS);
        builder.readTimeout(5, TimeUnit.SECONDS);
        builder.connectTimeout(5, TimeUnit.SECONDS);
        return builder.build();
    }

    private static final int MESSAGE_ONSUCCESS = 0;

    private static final int MESSAGE_ONFAIL = 1;
    private static final int MESSAGE_ONCODEERROR = 2;

     private static ResultListener mListener;

     private static Handler handler = new Handler(Looper.getMainLooper()){


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_ONSUCCESS:
                    Object object = msg.obj;
                    mListener.onSuccess(object);
                    break;
                case MESSAGE_ONFAIL:
                    IOException e = (IOException) msg.obj;
                    mListener.onFail(e);
                    break;
                case MESSAGE_ONCODEERROR:
                    String message = (String) msg.obj;
                    mListener.onCodeError(message);
                    break;
            }
        }
    };



//get请求
    public static <R> void executeGet(String url, final ResultListener<R> listener,final Class<R> clazz) {

        mListener = listener;

        final Request request = new Request.Builder().url(url).get().build();

        execute(request, listener,clazz);

    }
//post请求
    public static <R> void executePost(String url, Map<String, String> map, final ResultListener<R> listener,Class<R> clazz) {
        FormBody.Builder builder = new FormBody.Builder();
        mListener = listener;

        if (map != null) {
            Set<String> keySet = map.keySet();

            for (String key : keySet) {
                builder.add(key, map.get(key));
            }
        }


        FormBody requestBody = builder.build();

        final Request request = new Request.Builder().url(url).post(requestBody).build();

        execute(request, listener,clazz);

    }

    private static <R> void execute(Request request,final ResultListener<R> listener,final Class<R> clazz) {
        mListener = listener;
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.what = MESSAGE_ONFAIL;
                message.obj = e;
//                listener.onFail(e);
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseStr = response.body().string();

                Gson gson = new Gson();

                BaseEntity<R> baseEntity = gson.fromJson(responseStr, new TypeToken<BaseEntity<R>>() {
                }.getType());


                if (baseEntity.isSuccess()) {
                    String jsonStr=gson.toJson(baseEntity.getData());
                    R data=gson.fromJson(jsonStr,clazz);
//                    Log.i("TAG", "onResponse: "+data);
                    Message message = new Message();
                    message.what = MESSAGE_ONSUCCESS;
                    message.obj = data;
                    handler.sendMessage(message);
//                    listener.onSuccess(data);
                } else {
                    Message message = new Message();
                    message.what = MESSAGE_ONCODEERROR;
                    message.obj = baseEntity.getMsg();
                    handler.sendMessage(message);
//                    listener.onCodeError(baseEntity.getMsg());

                }
            }
        });
    }


}
