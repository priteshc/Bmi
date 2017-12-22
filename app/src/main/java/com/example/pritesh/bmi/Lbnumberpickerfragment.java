package com.example.pritesh.bmi;

/**
 * Created by yashwant on 6/8/2016.
 */
import android.app.Dialog;
import android.app.DialogFragment;


import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class Lbnumberpickerfragment extends DialogFragment implements OnClickListener {
    public static final String TAG = "NumberUnitPickerFragment";
    private View mView;
    private String title;
    private int titleId;
    private String message;
    private int messageId;
    private String positiveButtonText = "Set";
    private int positiveButtonId;
    private String negativeButtonText = "Cancel";
    private int negativeButtonId;
    private Drawable icon;
    private int iconId;
    private String unit = "lbs";
    private Integer unitId = 0;

    private int maxValue = 700;
    private int minValue = 0;
    private int value = 0;
    private boolean twoDigitsValues = true;

    private NumberPicker numberPicker;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        LinearLayout viewGroup = new LinearLayout(getActivity());
        viewGroup.setOrientation(LinearLayout.HORIZONTAL);
        viewGroup.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        viewGroup.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout numberLayout = new LinearLayout(getActivity());
        numberLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        numberLayout.setOrientation(LinearLayout.HORIZONTAL);
        numberLayout.setGravity(Gravity.CENTER_VERTICAL);


        // NumberPicker
        numberPicker = new NumberPicker(getActivity());
        if (maxValue > 0)
            numberPicker.setMaxValue(maxValue);
        if (minValue > 0)
            numberPicker.setMinValue(minValue);

     /*   if (twoDigitsValues)
            numberPicker.setFormatter(new NumberPicker.Formatter() {
                @Override
                public String format(int i) {
                    return String.format("%02d", i);
                }
            });*/

        if (value > 0)
            numberPicker.setValue(value);

        numberLayout.addView(numberPicker);

        // Unit
        if (!TextUtils.isEmpty(unit) || unitId != null) {
            TextView unitTextView = new TextView(getActivity());

            if (unitId > 0)
                unitTextView.setText(unitId);
            else
                unitTextView.setText(unit);

            LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            ll.setMargins(25, 0, 0, 0);
            unitTextView.setLayoutParams(ll);

            numberLayout.addView(unitTextView);
        }

        viewGroup.addView(numberLayout);

        alertDialogBuilder.setView(viewGroup);

        // Set title
        if (!TextUtils.isEmpty(title))
            alertDialogBuilder.setTitle(title);
        else if (titleId > 0)
            alertDialogBuilder.setTitle(titleId);

        // Set message
        if (!TextUtils.isEmpty(message))
            alertDialogBuilder.setMessage(message);
        else if (messageId > 0)
            alertDialogBuilder.setMessage(messageId);

        // Set icon
        if (icon != null)
            alertDialogBuilder.setIcon(icon);
        else if (iconId > 0)
            alertDialogBuilder.setIcon(iconId);

        // Set dialog positive button
        if (positiveButtonId > 0)
            alertDialogBuilder.setPositiveButton(positiveButtonId, this);
        else
            alertDialogBuilder.setPositiveButton(positiveButtonText, this);

        // Set dialog negative button
        if (negativeButtonId > 0)
            alertDialogBuilder.setNegativeButton(negativeButtonId, this);
        else
            alertDialogBuilder.setNegativeButton(negativeButtonText, this);

        return alertDialogBuilder.create();
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setView(View v) {
        this.mView = v;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitle(int title) {
        this.titleId = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessage(int message) {
        this.messageId = message;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setUnit(int unit) {
        this.unitId = unit;
    }

    public void setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
    }

    public void setPositiveButtonText(int positiveButtonText) {
        this.positiveButtonId = positiveButtonText;
    }

    public void setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
    }

    public void setNegativeButtonText(int negativeButtonText) {
        this.negativeButtonId = negativeButtonText;
    }

    public void setDrawable(Drawable drawable) {
        this.icon = drawable;
    }

    public void setDrawable(int drawable) {
        this.iconId = drawable;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setTwoDigitsValues(boolean twoDigitsValues) {
        this.twoDigitsValues = twoDigitsValues;
    }




    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:


                int my = numberPicker.getValue();

                Intent openlist = new Intent(getActivity(), Myreceiver.class);
                openlist.putExtra("name", String.valueOf(my));
                getActivity().sendBroadcast(openlist);

                break;

            case DialogInterface.BUTTON_NEGATIVE:

                break;
        }
    }
    
}
