package com.bawei.wuruitao.utils;

import com.bawei.wuruitao.api.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ProjectName: WuRuitao20200106
 * PackageName: com.bawei.wuruitao.utils
 * ClassName:   RetrofitUtil
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/6_8:45
 */
public class RetrofitUtil {
    private static RetrofitUtil retrofitUtil;
    private final Retrofit retrofit;

    private RetrofitUtil() {
        //网络接口数据请求使用Retrofit+OKHTTP
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //设置OKHTTP的日志拦截器，打印请求的日志信息
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtil getInstance() {
        if (retrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofitUtil == null) {
                    retrofitUtil = new RetrofitUtil();
                }
            }
        }
        return retrofitUtil;
    }


    public <T> T createService(Class<T> tClass) {
        return retrofit.create(tClass);
    }


}
