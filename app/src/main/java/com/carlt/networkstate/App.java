package com.carlt.networkstate;

import android.app.Application;

import com.carlt.networklibs.NetworkManager;

/**
 * Description:
 * Company    : carlt
 * Author     : zhanglei
 * Date       : 2019/2/26 17:03
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManager.getInstance().init(this);
    }
}
