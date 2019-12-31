package com.bawei.dinghongxiang20191231.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {

    Handler handler=new Handler();

    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;


    public static OkHttpUtils getInstance() {

        if (okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    okHttpUtils=new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    private OkHttpUtils(){
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }


    public void doGet(String url,OkHttpCallBack okHttpCallBack){
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                okHttpCallBack.failure(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result=response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okHttpCallBack.success(result);
                    }
                });
            }
        });
    }


    public boolean getNEt(Context context){

        ConnectivityManager cr= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo ao = cr.getActiveNetworkInfo();

        if (ao!=null&ao.isAvailable()){
            return true;
        }

        return false;

    }

    public interface OkHttpCallBack{
        void success(String json);
        void failure(Throwable throwable);
    }
}
