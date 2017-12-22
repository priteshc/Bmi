package com.example.pritesh.bmi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.example.pritesh.bmi.R;

public class SpeedometerView extends View {
	
	
	Bitmap needle = null;
	Bitmap speedo_meter = null;
	Bitmap center_wheel = null;
	Bitmap counter_text = null;
	
	Paint paint_needle, paint_text;
	
	Context context;
	
	SpeedometerView speedo_obj;
	
	String priceValue;
	
	public static float minAngle = (float) 246.5;

	public static float maxAngle = (float) 110.5;
	
	public static float exact_angle = 0; 

	public static float angle_of_deviation = minAngle;
	
	public static float maxValue = 100;
	
	int width,height;
	
	Matrix matrix_needle;
	

	public SpeedometerView(Context context) {
		super(context);
		initializeView(context);
		
	}
	
	public SpeedometerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeView(context);
	}
	public SpeedometerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initializeView(context);
	}
	
	
	//Create view
	private void initializeView(Context context) {
		
		this.context = context;
		speedo_obj = this;
		

		this.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		
		releaseImageResources();

		speedo_meter = getImage(R.drawable.indicator);

		center_wheel = getImage(R.drawable.center_wheel);

	//	counter_text = getImage(R.drawable.counter_text_gradient);

		needle = getImage(R.drawable.kata);
		
		paint_needle = new Paint();
		paint_needle.setStyle(Paint.Style.FILL);
		paint_needle.setAntiAlias(true);

		paint_text = new Paint();
		paint_text.setColor(Color.WHITE);
		paint_text.setAntiAlias(true);
		paint_text.setStyle(Paint.Style.FILL_AND_STROKE);
		paint_text.setTextSize(40);
		
		
	}

	protected void onDraw(Canvas canvas) {
		
		
		canvas.drawBitmap(speedo_meter, (canvas.getWidth() / 2)- speedo_meter.getWidth() / 2,
				 (canvas.getHeight() / 9)- speedo_meter.getHeight() / 8, null);


			// Main Meter Needle
			matrix_needle = new Matrix();
			matrix_needle.setTranslate((canvas.getWidth() / 2)- needle.getWidth() / 2,
					(canvas.getHeight() / 4)- needle.getHeight()/3);
			Log.d("ANGLE OF DEVIATION : ",""+angle_of_deviation);
			
			matrix_needle.postRotate(angle_of_deviation,
					canvas.getWidth() / 2, 2 * needle.getHeight() - 90);

			canvas.drawBitmap(needle, matrix_needle, paint_needle);


			// Main Meter Wheel
			canvas.drawBitmap(center_wheel, (canvas.getWidth() / 2)- center_wheel.getWidth() / 2,
					(canvas.getHeight() / 2)- center_wheel.getHeight() / 1, null);


			
			//speed value
			priceValue = Float.toString(exact_angle);

			if (exact_angle < 100) {

				priceValue = "0 " + priceValue.charAt(0) + " "
						+ priceValue.charAt(1);

				//Log.d("Formatted Price from if : ", priceValue);
			} else {
				priceValue = priceValue.charAt(0) + " " + priceValue.charAt(1)
						+ " " + priceValue.charAt(2);
			}

			/*canvas.drawText(priceValue, speedo_meter.getWidth() / 2
					- counter_text.getHeight() + 15, speedo_meter.getHeight()
					- counter_text.getWidth() - 36, paint_text);
*/
			// Counter Text
/*
			canvas.drawBitmap(counter_text, speedo_meter.getWidth() / 2
					- counter_text.getWidth() + 50, speedo_meter.getHeight()
					- counter_text.getHeight() - 130, null);
*/



	}
	
	
	public void calculateAngleOfDeviation(int randomly_generated_value) {

		exact_angle = (float) randomly_generated_value;
		
		float angleDef = (float) (maxAngle + minAngle - 130);

		angle_of_deviation = ((exact_angle * angleDef) / maxValue) + minAngle;

		speedo_obj.invalidate();

	}
	
	public Bitmap getImage(int res_id) {
		return BitmapFactory.decodeResource(getResources(), res_id);

	}
	public void releaseImageResources() {
		if (speedo_meter != null)
			speedo_meter = null; 
		if (needle != null)
			needle = null;
		if (center_wheel != null)
			center_wheel = null;
	}

}
