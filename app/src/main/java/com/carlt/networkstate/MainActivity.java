package com.carlt.networkstate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.carlt.networklibs.NetType;
import com.carlt.networklibs.NetworkManager;
import com.carlt.networklibs.annotation.NetWork;
import com.carlt.networklibs.utils.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册
        NetworkManager.getInstance().registerObserver(this);
    }

    @NetWork(netType = NetType.AUTO)
    public void network(NetType netType) {
        switch (netType) {
            case WIFI:
                Log.e(Constants.LOG_TAG, "wifi");
                break;
            case CMNET:
            case CMWAP:
                Log.e(Constants.LOG_TAG, "4G");
                break;
            case AUTO:
                break;
            case NONE:
                Log.e(Constants.LOG_TAG, "无网络");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //反注册 
        NetworkManager.getInstance().unRegisterObserver(this);
    }
}
