package com.example.pritesh.bmi;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.datetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yashwant on 6/27/2016.
 */
public class AddWeight extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {


    TextView weight,date,time;

    Cursor c1,c2,c3;

    private Calendar calendar;
    private DateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private static final String TIME_PATTERN = "HH:mm";

    Button save;

    String myheight,myweight,day,mydate,mytime,idi,weg,wtt;

    Double cmm = 100.00;

    int count,id,eid;

    SharedPreferences clk;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addweight);

        clk = this.getSharedPreferences("MY", 0);

        weight = (TextView) findViewById(R.id.weight);

        date = (TextView) findViewById(R.id.height);

        time = (TextView) findViewById(R.id.time2);

        save = (Button) findViewById(R.id.calcult);


        Calendar c = Calendar.getInstance();

        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat date1 = new SimpleDateFormat("HH:mm");

        time.setText(date1.format(c.getTime()));

        SimpleDateFormat df = new SimpleDateFormat("dd,MMM,yyyy");
        date.setText(df.format(c.getTime()));


        BmiDatabase bmiDatabase = new BmiDatabase(AddWeight.this);

        bmiDatabase.open();


        c1 = bmiDatabase.getdata();

        for (c1.moveToFirst(); !c1.isAfterLast(); c1.moveToNext()) {


            myweight = c1.getString(c1.getColumnIndex(bmiDatabase.KEY_WEIGHT));
            myheight = c1.getString(c1.getColumnIndex(bmiDatabase.KEY_HIGHT));
            mydate = c1.getString(c1.getColumnIndex(bmiDatabase.KEY_DATE));
            mytime = c1.getString(c1.getColumnIndex(bmiDatabase.KEY_TIME));
            eid = c1.getInt(c1.getColumnIndex(bmiDatabase.KEY_BID));


        }

        bmiDatabase.close();

        System.out.println("high:" + myheight);

        weight.setText(myweight);

        date.setText(mydate);

        time.setText(mytime);


        if (clk.contains("key"))
        {
            if (getIntent().getExtras().getString("wight") != null) {


                weight.setText(getIntent().getExtras().getString("wight"));
                date.setText(getIntent().getExtras().getString("date"));
                time.setText(getIntent().getExtras().getString("time"));
                idi = getIntent().getExtras().getString("ids");

            }

    }

        clk.edit().clear().apply();

        weight.setOnClickListener(this);
        date.setOnClickListener(this);
        time.setOnClickListener(this);
        save.setOnClickListener(this);


        calendar = Calendar.getInstance();
        dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        timeFormat = new SimpleDateFormat(TIME_PATTERN, Locale.getDefault());



    }


    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.weight:


                FragmentManager fm = getFragmentManager();
                NumberPickerFragment dialogFragment = new NumberPickerFragment();
                dialogFragment.show(fm, "Sample Fragment");
                dialogFragment.setCancelable(false);

                break;


            case R.id.height:


                DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");


                break;

            case R.id.time2:


                TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show(getFragmentManager(), "timePicker");


                break;


            case R.id.calcult:

                String weight1 = weight.getText().toString();

                String time1 = time.getText().toString();

                String date1 = date.getText().toString();

             Double   dw = Double.parseDouble(myheight)/cmm;

              Double  dh = Double.parseDouble(weight1)/(dw*dw);

                String mybm = String.format("%.2f", dh);

                SimpleDateFormat df = new SimpleDateFormat("dd,MMM,yyyy");

                try {
                    Date date = df.parse(date1);

                    SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                    day = outFormat.format(date);

                    System.out.println("My:" + day);

                }
                catch (Exception e){

                    e.printStackTrace();
                }


                BmiDatabase bmiDatabase = new BmiDatabase(AddWeight.this);
                bmiDatabase.open();


                c2 = bmiDatabase.valid(date1);

                for(c2.moveToFirst();!c2.isAfterLast();c2.moveToNext()){

                count = c2.getCount();

                    id = c2.getInt(c2.getColumnIndex(bmiDatabase.KEY_BID));


                }






                if(count <= 0){

                    int i = Integer.parseInt(myweight) - Integer.parseInt(weight1);
                    String dff = String.valueOf(i);

                    bmiDatabase.cretweightentry(weight1,myheight,time1,date1,mybm,day,dff);
                    Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();

                }
                else{



                    if(count > 0){


                        if(id > 1) {
                            int pi = id - 1;
                            String pii = String.valueOf(pi);


                            c3 = bmiDatabase.getweight(pii);

                            for (c3.moveToFirst(); !c3.isAfterLast(); c3.moveToNext()) {

                                weg = c3.getString(c3.getColumnIndex(bmiDatabase.KEY_WEIGHT));

                            }

                            int wt = Integer.parseInt(weg) - Integer.parseInt(weight1);

                             wtt = String.valueOf(wt);
                        }
                        else{


                            wtt = "0";
                        }

                        bmiDatabase.updateweightentry(id,weight1,myheight,time1,date1,mybm,day,wtt);

                        Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();

                    }

                }



                bmiDatabase.close();

                Intent intent = new Intent(AddWeight.this,BottomActivity.class);
                 startActivity(intent);
                finish();





                break;



        }



    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {

        calendar.set(year, monthOfYear, dayOfMonth);


        try
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, monthOfYear);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM");
            simpleDateFormat.setCalendar(calendar);
         String   monthName = simpleDateFormat.format(calendar.getTime());
            String dr = String.valueOf(dayOfMonth+","+monthName+","+year);
            date.setText(dr);
        }
        catch (Exception e)
        {
            if(e!=null)
                e.printStackTrace();
        }



    }



    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        time.setText(hourOfDay+":"+minute);


    }


    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("myevent"));
    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
        String    weight1 = intent.getStringExtra("myname");

            System.out.println("We:" + weight1);

            weight.setText(weight1);

        }

    };


    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }
}
