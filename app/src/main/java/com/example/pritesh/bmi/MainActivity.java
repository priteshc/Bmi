package com.example.pritesh.bmi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.datetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener {


    EditText name,dob,addresss,city,pincode;
    Button submit;
    ImageButton date;

    String gender;
    RadioGroup radioGroup;

    private Calendar calendar;
    private DateFormat dateFormat;

    SharedPreferences reg;

    String age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg = this.getSharedPreferences("REG",0);

        name = (EditText)findViewById(R.id.name1);
        dob = (EditText)findViewById(R.id.dob1);
        addresss = (EditText)findViewById(R.id.address1);
        city = (EditText)findViewById(R.id.city1);
        pincode = (EditText)findViewById(R.id.pincode1);


        radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);



        submit = (Button)findViewById(R.id.submit);

        date = (ImageButton) findViewById(R.id.date);


        submit.setOnClickListener(this);
        date.setOnClickListener(this);

        calendar = Calendar.getInstance();
        dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.date:

                Animation myAnim2 = AnimationUtils.loadAnimation(this, R.anim.milkshake);
                findViewById(R.id.date).startAnimation(myAnim2);

                DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");


                break;



            case R.id.submit:

                Animation myAnim1 = AnimationUtils.loadAnimation(this, R.anim.milkshake);
                findViewById(R.id.submit).startAnimation(myAnim1);

                 String name2 = name.getText().toString();
                String dob2 = dob.getText().toString();

                int selectedId=radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(selectedId);
                int radioId = radioGroup.indexOfChild(radioButton);

                String addresss2 = addresss.getText().toString();
                String city2 = city.getText().toString();
                String pincode2 = pincode.getText().toString();


                if(name2.equals("")){

                    Toast.makeText(this,"Plz enter your name",Toast.LENGTH_SHORT).show();

                }
                else{

                    if(dob2.equals("")){

                        Toast.makeText(this,"Plz enter your dob",Toast.LENGTH_SHORT).show();

                    }
                    else{

                        if(radioId == -1){

                            Toast.makeText(this,"Plz select ur gender",Toast.LENGTH_SHORT).show();

                        }
                        else{

                            if(addresss2.equals("")){

                                Toast.makeText(this,"Plz enter ur adress",Toast.LENGTH_SHORT).show();


                            }
                            else{

                                if(city2.equals("")){

                                    Toast.makeText(this,"Plz enter ur city",Toast.LENGTH_SHORT).show();

                                }

                                else{

                                    if(pincode2.equals("")){

                                        Toast.makeText(this,"Plz enter ur pincode",Toast.LENGTH_SHORT).show();


                                    }

                                    else{

                                        if(radioId == 0){

                                        gender = "male";

                                        }
                                        if(radioId == 1){

                                            gender = "female";
                                        }


                                       BmiDatabase bm = new BmiDatabase(this);

                                        bm.open();

                                        bm.createntry(name2,dob2,gender,addresss2,city2,pincode2,age);

                                        bm.close();

                                        Toast.makeText(this,"Registration Successfull",Toast.LENGTH_SHORT).show();

                                        reg.edit().putString("log","login").apply();

                                        Intent i = new Intent(MainActivity.this,Bmicalci.class);

                                        startActivity(i);

                                    }



                                }

                            }

                        }





                    }





                }





                break;



        }





    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {

        calendar.set(year, monthOfYear, dayOfMonth);
        // update();
        int month = monthOfYear + 1;

        dob.setText(dayOfMonth+"/"+month+"/"+year);



        final Calendar c1 = Calendar.getInstance();

        int  y = c1.get(Calendar.YEAR);

        int tt = y - year;

        age = String.valueOf(tt);

    }
}
