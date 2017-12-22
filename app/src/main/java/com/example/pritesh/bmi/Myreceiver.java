package com.example.pritesh.bmi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by yashwant on 1/23/2016.
 */
public class Myreceiver extends BroadcastReceiver {

  SharedPreferences shr;

    @Override
    public void onReceive(Context context, Intent intent) {


        String weight = intent.getStringExtra("name");



        intent = new Intent("myevent");
        intent.putExtra("myname",weight);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


    }
}
