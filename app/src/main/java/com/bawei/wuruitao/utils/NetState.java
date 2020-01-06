package com.bawei.wuruitao.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * ProjectName: WuRuitao20200106
 * PackageName: com.bawei.wuruitao.utils
 * ClassName:   NetState
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/6_8:50
 */
public class NetState {
    private static NetState netState;

    private NetState() {

    }

    public static NetState getInstance() {
        if (netState == null) {
            synchronized (NetState.class) {
                if (netState == null) {
                    netState = new NetState();
                }
            }
        }
        return netState;
    }
//v.封装网络状态判断的方法，可以判断有网，无网

    public boolean hashNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            return true;
        }

        return false;
    }



}
