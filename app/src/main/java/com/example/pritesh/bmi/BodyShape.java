package com.example.pritesh.bmi;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yashwant on 7/2/2016.
 */
public class BodyShape extends Fragment implements View.OnClickListener {


    Button cal;

    EditText waist,hips;

    String gender;

    Cursor c1;

    TextView tet,tet1;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {


        return inflater.inflate(R.layout.bodyshape, container, false);


    }


    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        View v = getView();

        BmiDatabase bmiDatabase = new BmiDatabase(getContext());

        bmiDatabase .open();

        c1 = bmiDatabase.getgen();

        for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext()){

          gender = c1.getString(c1.getColumnIndex(bmiDatabase.KEY_GENDER));

        }


        waist = (EditText)v.findViewById(R.id.weight);

        hips = (EditText)v.findViewById(R.id.height);

        cal = (Button)v.findViewById(R.id.calcult);

        tet = (TextView) v.findViewById(R.id.txt);
        tet1 = (TextView) v.findViewById(R.id.txt1);


        cal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.calcult:

                Animation myAnim1 = AnimationUtils.loadAnimation(getContext(), R.anim.milkshake);
                getActivity().findViewById(R.id.calcult).startAnimation(myAnim1);

                String wai = waist.getText().toString();

                String hip = hips.getText().toString();

                if(wai.equals("")){

                    Toast.makeText(getContext(),"Plz enter waist size",Toast.LENGTH_SHORT).show();

                }
                else {

                    if (wai.equals("0")) {

                        Toast.makeText(getContext(), "Plz enter valid waist size", Toast.LENGTH_SHORT).show();

                    } else {

                        if (hip.equals("")) {

                            Toast.makeText(getContext(), "Plz enter hips size", Toast.LENGTH_SHORT).show();

                        } else {

                            if (hip.equals("0")) {

                                Toast.makeText(getContext(), "Plz enter valid hips size", Toast.LENGTH_SHORT).show();

                            } else {

                                Double d0 = Double.valueOf(wai) / Double.valueOf(hip);

                                String mybm = String.format("%.2f", d0);

                                Double d1 = Double.valueOf(mybm);

                                if (gender.equals("male")) {

                                    if (d1 <= 0.95) {

                                        tet.setText("Your body shape is Pear");
                                        tet1.setText("Your waist:hips ratio is "+mybm);

                                    } else {

                                        if (d1 > 0.95 && d1 <= 1.0) {

                                            tet.setText("Your body shape is Avocado");
                                            tet1.setText("Your waist:hips ratio is "+mybm);

                                        } else {

                                            if (d1 > 1.0) {

                                                tet.setText("Your body shape is Apple");
                                                tet1.setText("Your waist:hips ratio is "+mybm);

                                            }

                                        }

                                    }

                                } else {

                                    if (gender.equals("female")) {

                                        if (d1 <= 0.80) {

                                            tet.setText("Your body shape is Pear");
                                            tet1.setText("Your waist:hips ratio is "+mybm);

                                        } else {

                                            if (d1 > 0.80 && d1 <= 0.85) {

                                                tet.setText("Your body shape is Avocado");
                                                tet1.setText("Your waist:hips ratio is "+mybm);

                                            } else {

                                                if (d1 > 0.85) {

                                                    tet.setText("Your body shape is Apple");
                                                    tet1.setText("Your waist:hips ratio is " +mybm);

                                                }


                                            }

                                        }


                                    }


                                }


                            }
                        }
                    }
                }




                break;



        }

    }
}
