package com.example.pritesh.bmi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yashwant on 6/25/2016.
 */
public class Splash extends AppCompatActivity {


    SharedPreferences reg;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


         setContentView(R.layout.splash);

        reg = this.getSharedPreferences("REG",0);


        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                finally{


                    if(reg.contains("log")){

                        Intent io2 = new Intent(Splash.this,BottomActivity.class);
                        startActivity(io2);
                        finish();

                    }else {

                        Intent openlist = new Intent(Splash.this,MainActivity.class);
                        startActivity(openlist);
                        finish();
                    }


                }
            }

        };
        timer.start();

    }
}
