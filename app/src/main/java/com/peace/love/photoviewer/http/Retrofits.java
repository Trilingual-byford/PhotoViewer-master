package com.peace.love.photoviewer.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ProjectName:PhotoViewer
 * Date:2018/3/2 11:28
 *
 * @author CBF
 *
 * init Retrofits, using Gson to reslove json
 */

public class Retrofits {

    private static Retrofit sRetrofit;
    private static Retrofit get(){
        if (sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                    .baseUrl("https://api.photozou.jp")
                    .client(Client.get())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    public static <T> T get(Class<T> tClass){
        return get().create(tClass);
    }
}
