package com.example.pritesh.bmi;

/**
 * Created by yashwant on 6/21/2016.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by yashwant on 1/23/2016.
 */
public class Myreceiver1 extends BroadcastReceiver {

    SharedPreferences shr;

    @Override
    public void onReceive(Context context, Intent intent) {


        String heeight = intent.getStringExtra("height");

        intent = new Intent("newevent");
        intent.putExtra("myhet",heeight);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


    }
}
