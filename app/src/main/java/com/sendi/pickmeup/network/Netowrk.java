package com.sendi.pickmeup.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sendi.pickmeup.base.BaseEntity;
import com.sendi.pickmeup.listener.ResultListener;

import java.io.IOException;
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

public class Netowrk {
    private static OkHttpClient mOkHttpClient = initOkHttpClient();

    private static OkHttpClient initOkHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.writeTimeout(5, TimeUnit.SECONDS);
        builder.readTimeout(5, TimeUnit.SECONDS);
        builder.connectTimeout(5, TimeUnit.SECONDS);
        return builder.build();
    }

//get请求
    public static <R> void executeGet(String url, final ResultListener<R> listener) {

        final Request request = new Request.Builder().url(url).get().build();

        execute(request, listener);

    }
//post请求
    public static <R> void executePost(String url, Map<String, String> map, final ResultListener<R> listener) {
        FormBody.Builder builder = new FormBody.Builder();

        if (map != null) {
            Set<String> keySet = map.keySet();

            for (String key : keySet) {
                builder.add(key, map.get(key));
            }
        }


        FormBody requestBody = builder.build();

        final Request request = new Request.Builder().url(url).post(requestBody).build();

        execute(request, listener);

    }

    private static <R> void execute(Request request, final ResultListener<R> listener) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onFail(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();

                Gson gson = new Gson();

                BaseEntity<R> baseEntity = gson.fromJson(responseStr, new TypeToken<BaseEntity<R>>() {
                }.getType());


                if (baseEntity.isSuccess()) {
                    listener.onSuccess(baseEntity.getData());
                } else {
                    listener.onCodeError(baseEntity.getMsg());
                }
            }
        });
    }


}
