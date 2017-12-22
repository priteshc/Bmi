package com.example.pritesh.bmi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pritesh.bmi.utils.SpeedPreference;
import com.example.pritesh.bmi.view.SpeedometerView;

import java.util.Random;

public class SpeedometerActivity extends Fragment {

	private long delay = 10000;

	SpeedometerView mView;

	ImageView arrowImag;
	TextView bmii;

	static Handler handler;

	String result;

	int startAngle;

	String priceStr, speedValueStr;

	Thread threadMainMeter;
	Button changeNeedleValueBtn;


	SpeedPreference speedPreference;

	SharedPreferences bmi1;

	int a ;
	String mybmi;
	Cursor c1;



	@Override
	public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

		return inflater.inflate(R.layout.speedometer, container, false);

	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		View v = getView();

		bmii = (TextView)v.findViewById(R.id.tet);

		bmi1 = getContext().getSharedPreferences("BMI",1);

		WebView	web = (WebView)v. findViewById(R.id.webView);

		web.getSettings().setJavaScriptEnabled(true);


		if(bmi1.contains("bmical")){


			mybmi = bmi1.getString("bmical","");

		}



		if(!bmi1.contains("bmical")){

			BmiDatabase bm = new BmiDatabase(getContext());
			bm.open();

		  c1 =	bm.getdata();


			for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext()){


				mybmi = c1.getString(c1.getColumnIndex(bm.KEY_BMI));


			}

			bm.close();


		}



		bmi1.edit().clear().apply();



		bmii.setText("Your bmi is" + mybmi);




		Double bb = Double.parseDouble(mybmi);




		if( bb < 18.5){


           a = 20;
		}
		else {

			if (bb >= 18.5 && bb <= 25) {


				a = 40;

			} else {

				if (bb >= 25 && bb <= 30) {


					a = 60;

				} else {
					if (bb >= 30 && bb <= 40) {

						a = 72;

					} else {
						if (bb > 40) {

							a = 83;
						}


					}

				}

			}
		}

		initUI();

		speedPreference = new SpeedPreference(getContext());

		speedPreference.setPreviousNeedleValue("0");

		moveNeedle();



		web.setBackgroundColor(Color.TRANSPARENT); //for gif without background
		web.loadUrl("file:///android_asset/my.html");

	}

	private void moveNeedle() {

		handler = new Handler() {

			public void handleMessage(Message msg) {

				Bundle b = msg.getData();
				int key = b.getInt("angle_in_degrees", 0);

				if (key == 0) {
					
				} else {
					mView.calculateAngleOfDeviation(key);
				}

			};
		};
		handler.postDelayed(null, delay);

		threadMainMeter = new Thread(new Runnable() {

			@Override
			public void run() {

				startAngle = Integer.parseInt(speedPreference
						.getPreviousNeedleValue());

				generateValue(a);

				if (Integer.parseInt(speedValueStr) > 100) {
					speedValueStr = "100";
				}
				if (startAngle > Integer.parseInt(speedValueStr)) {
					for (int i = startAngle; i >= Integer
							.parseInt(speedValueStr); i = i - 1) {

						try {
							Thread.sleep(15);
							Message msg = new Message();
							Bundle b = new Bundle();
							b.putInt("angle_in_degrees", i);
							msg.setData(b);
							handler.sendMessage(msg);

						} catch (InterruptedException e) {

							e.printStackTrace();
						}
					}

				} else {
					for (int i = startAngle; i <= Integer
							.parseInt(speedValueStr); i = i + 1) {

						try {
							Thread.sleep(15);
							Message msg = new Message();
							Bundle b = new Bundle();
							b.putInt("angle_in_degrees", i);
							msg.setData(b);
							handler.sendMessage(msg);

						} catch (InterruptedException e) {

							e.printStackTrace();
						}
					}
				}
			}
		});

		threadMainMeter.start();

	}

	private void generateValue(int a) {

		Random r = new Random();
	//	speedValueStr = String.valueOf(r.nextInt(100 - 10) + 10);

			speedValueStr = String.valueOf(a);

       System.out.println("Speed:" + speedValueStr);

		speedPreference.setPreviousNeedleValue(speedValueStr);

	}

	private void initUI() {

		mView = (SpeedometerView)getActivity().findViewById(R.id.speedometer_view);
/*
		changeNeedleValueBtn = (Button) findViewById(R.id.change_needle_value_btn);
		changeNeedleValueBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				moveNeedle();

			}
		});*/

	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	public void onResume() {
		super.onResume();

	}

/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.speedometer, menu);
		return true;
	}
*/

}
