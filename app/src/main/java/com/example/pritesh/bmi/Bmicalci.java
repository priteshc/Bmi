package com.example.pritesh.bmi;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yashwant on 6/3/2016.
 */
public class Bmicalci extends AppCompatActivity implements View.OnClickListener  {

    RadioGroup weight,height;
    RadioButton inkg,inpound,incms,ininch;

    Button calculate;

    TextView myweight,myheight;

    Double lbs = 2.2046;

    Double inch = 0.39370;

    Double metr = 0.010000;

    Double cmm = 100.00;

    TextView t1 ,t2;

    String wg,hg;

    Double dw,dh;

    Myvalue myvalue;

    Date date;

    EditText my;
    char t11,t12,t13;
    String day;

    NumberPickerFragment  numberPickerFragment;

    String weight1,height1;

    SharedPreferences bmi;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bmicalcult);

        bmi = this.getSharedPreferences("BMI",1);


        weight = (RadioGroup)findViewById(R.id.radioGroup1);


        my = (EditText) findViewById(R.id.bmi);


        myweight = (TextView) findViewById(R.id.weight);

        myheight = (TextView) findViewById(R.id.height);


        inkg = (RadioButton)findViewById(R.id.kg);

        inpound = (RadioButton)findViewById(R.id.pound);

        calculate = (Button)findViewById(R.id.calcult);




        t1 = (TextView)findViewById(R.id.label);

        t2 = (TextView)findViewById(R.id.label1);

        weight.setOnClickListener(this);





        calculate.setOnClickListener(this);


        myweight.setOnClickListener(this);

        myheight.setOnClickListener(this);


        inkg.setOnClickListener(this);
        inpound.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.kg:

                t1.setText("kgs");
                t2.setText("cm");


                String mylb12 = myweight.getText().toString();

                String hig1 = myheight.getText().toString();

                if(!mylb12.equals("")) {
                    Double dd12 = Double.parseDouble(mylb12) / lbs;

                    myweight.setText(String.format("%.2f", dd12));

                }

                if(!hig1.equals("")) {


                    int ii = hig1.length();



                    System.out.println("Char:" + ii);


                    if(ii==4) {

                        for (int i = 0; i < hig1.length(); i++) {

                             t11 = hig1.charAt(0);

                             t12 = hig1.charAt(2);

                        }


                        int t0 = (Character.getNumericValue(t11)*12) + Character.getNumericValue(t12);


                        Double t01 = t0 * 2.54;

                        int a = (int) Math.round(t01);


                        myheight.setText(String.valueOf(a));


                    }
                    else{

                        if(ii==5){

                            for (int i = 0; i < hig1.length(); i++) {

                                 t11 = hig1.charAt(0);

                                t12 = hig1.charAt(2);
                                t13 = hig1.charAt(3);

                            }

                            int tr = Character.getNumericValue(t12);

                            int tr1 = Character.getNumericValue(t13);

                            String tu = String.valueOf(tr)+""+String.valueOf(tr1);


                            int t0 = (Character.getNumericValue(t11)*12) + Integer.parseInt(tu) ;

                            System.out.println("Chart:" + t0);

                            Double t01 = t0 * 2.54;

                            int a = (int) Math.round(t01);

                            System.out.println("Chart1:" + t01);

                            myheight.setText(String.valueOf(a));


                        }




                    }


                }


                    break;


            case R.id.pound:

                t1.setText("lbs");
                t2.setText("ft/in");


                String mylb11 = myweight.getText().toString();

                String hig12 = myheight.getText().toString();

                if(!mylb11.equals("")) {

                    Double dd111 = Double.parseDouble(mylb11) * lbs;

                    myweight.setText(String.format("%.2f", dd111));


                }


                if(!hig12.equals("")) {

                    Double fett = Double.parseDouble(hig12) * inch;

                    int a = (int) Math.round(fett);

                    int q = a/12;

                    int r = a%12;

                    String ftt = String.valueOf(q)+"'"+String.valueOf(r)+"\"";

                    myheight.setText(ftt);

                }




                break;


            case R.id.weight:

                int selectedId2 =weight.getCheckedRadioButtonId();
                View radioButton2 = weight.findViewById(selectedId2);
                int radioId2 = weight.indexOfChild(radioButton2);

                Toast.makeText(this,"id:"+ radioId2,Toast.LENGTH_SHORT ).show();

                if(radioId2 == 0) {

                    FragmentManager fm = getFragmentManager();
                    NumberPickerFragment dialogFragment = new NumberPickerFragment();
                    dialogFragment.show(fm, "Sample Fragment");
                    dialogFragment.setCancelable(false);
                }
                else{
                    if(radioId2 == 1){

                        FragmentManager fm = getFragmentManager();
                        Lbnumberpickerfragment dialogFragment = new Lbnumberpickerfragment();
                        dialogFragment.show(fm, "Sample Fragment");
                        dialogFragment.setCancelable(false);


                    }



                }
                break;


            case R.id.height:


                int selectedId3 =weight.getCheckedRadioButtonId();
                View radioButton3 = weight.findViewById(selectedId3);
                int radioId3 = weight.indexOfChild(radioButton3);


                if(radioId3 == 0) {

                    FragmentManager fm = getFragmentManager();
                    Cmhightpicker dialogFragment = new Cmhightpicker();
                    dialogFragment.show(fm, "Sample Fragment");
                    dialogFragment.setCancelable(false);
                }
                else{
                    if(radioId3 == 1){

                        FragmentManager fm = getFragmentManager();
                        Hightpicker dialogFragment = new Hightpicker();
                        dialogFragment.show(fm, "Sample Fragment");
                        dialogFragment.setCancelable(false);


                    }



                }




                break;



            case R.id.calcult:

                Animation myAnim1 = AnimationUtils.loadAnimation(this, R.anim.milkshake);
                findViewById(R.id.calcult).startAnimation(myAnim1);


                int selectedId1=weight.getCheckedRadioButtonId();
                View radioButton1 = weight.findViewById(selectedId1);
                int radioId1 = weight.indexOfChild(radioButton1);



                Calendar c = Calendar.getInstance();

                SimpleDateFormat df = new SimpleDateFormat("dd,MMM,yyyy");
                String formattedDate = df.format(c.getTime());

                SimpleDateFormat df1 = new SimpleDateFormat("HH:mm");
                String time = df1.format(c.getTime());

                System.out.println("Current time => " +time);


                try {
                    Date date = df.parse(formattedDate);

                    SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                     day = outFormat.format(date);


                }
                catch (Exception e){

                    e.printStackTrace();
                }


                if(radioId1 == 0){


                    wg = myweight.getText().toString();

                    String hig11 = myheight.getText().toString();

                    Double dd12 = Double.parseDouble(hig11)/cmm;

                    hg = String.valueOf(dd12);




                }else {

                    if (radioId1 == 1) {


                        String hig14 = myheight.getText().toString();

                        String mylb1 = myweight.getText().toString();

                        Double dd11 = Double.parseDouble(mylb1) / lbs;

                        wg = String.valueOf(dd11);


                        if (!hig14.equals("")) {


                            int ii = hig14.length();


                            System.out.println("Char:" + ii);


                            if (ii == 4) {

                                for (int i = 0; i < hig14.length(); i++) {

                                    t11 = hig14.charAt(0);

                                    t12 = hig14.charAt(2);

                                }


                                int t0 = (Character.getNumericValue(t11) * 12) + Character.getNumericValue(t12);


                                Double t01 = t0 * 2.54;

                                int a = (int) Math.round(t01);

                                Double dd12 = Double.parseDouble(String.valueOf(a))/cmm;

                                hg = String.valueOf(dd12);





                            } else {

                                if (ii == 5) {

                                    for (int i = 0; i < hig14.length(); i++) {

                                        t11 = hig14.charAt(0);

                                        t12 = hig14.charAt(2);
                                        t13 = hig14.charAt(3);

                                    }

                                    int tr = Character.getNumericValue(t12);

                                    int tr1 = Character.getNumericValue(t13);

                                    String tu = String.valueOf(tr) + "" + String.valueOf(tr1);


                                    int t0 = (Character.getNumericValue(t11) * 12) + Integer.parseInt(tu);

                                    System.out.println("Chart:" + t0);

                                    Double t01 = t0 * 2.54;

                                    int a = (int) Math.round(t01);


                                    Double dd12 = Double.parseDouble(String.valueOf(a))/cmm;

                                    hg = String.valueOf(dd12);




                                }


                            }


                        }

                    }


                }


                 dw = Double.parseDouble(hg);


                 dh = Double.parseDouble(wg)/(dw*dw);

                 String mybm = String.format("%.2f", dh);


                Double gg = dw/metr;

                int a = (int) Math.round(gg);

                BmiDatabase bmiDatabase = new BmiDatabase(Bmicalci.this);
                bmiDatabase.open();

                String hig = String.valueOf(a);

                String weig = String.valueOf(wg);

                String diff = "0";

                bmiDatabase.bmientry(hig,weig,mybm,formattedDate,day,time,diff);

                bmiDatabase.close();

                Toast.makeText(this,"Entry Save Successfully",Toast.LENGTH_SHORT).show();

                bmi.edit().putString("bmical",mybm).apply();

                Intent i = new Intent(Bmicalci.this,BottomActivity.class);

                startActivity(i);


              //   mybmi.setText(mybm);








                 break;




        }


    }



    @Override
    protected void onResume() {
        super.onResume();


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("myevent"));


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver1,
                new IntentFilter("newevent"));


    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
            weight1 = intent.getStringExtra("myname");

            System.out.println("We:" + weight1);

            myweight.setText(weight1);

        }

    };

    private BroadcastReceiver mMessageReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
            height1 = intent.getStringExtra("myhet");


            myheight.setText(height1);

        }

    };




    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver1);

    }
}
