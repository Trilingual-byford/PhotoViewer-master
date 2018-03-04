package com.peace.love.photoviewer.http;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * ProjectName:PhotoViewer
 * Date:2018/3/2 11:21
 *
 * @author CBF
 *
 * init okHttp
 */

public class Client {
    private static OkHttpClient sOkHttpClient;
    private final static HttpLoggingInterceptor HTTP_LOGGING_INTERCEPTOR =
            new HttpLoggingInterceptor(message -> Log.d("Http:", message))
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    public static OkHttpClient get() {
        if (sOkHttpClient == null) {
            sOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(HTTP_LOGGING_INTERCEPTOR)        //log拦截器(print http info)
                    .connectTimeout(10, TimeUnit.SECONDS)   //连接超时
                    .readTimeout(30, TimeUnit.SECONDS)      //读超时
                    .writeTimeout(30,TimeUnit.SECONDS)      //写超时
                    .build();
        }
        return sOkHttpClient;
    }
}
