package com.example.pritesh.bmi;

/**
 * Created by yashwant on 6/30/2016.
 */
import com.example.pritesh.bmi.adapter.WeightAdapter;
import com.example.pritesh.bmi.chart.PointStyle;
import com.example.pritesh.bmi.model.XYMultipleSeriesDataset;
import com.example.pritesh.bmi.model.XYSeries;
import com.example.pritesh.bmi.renderer.BasicStroke;
import com.example.pritesh.bmi.renderer.XYMultipleSeriesRenderer;
import com.example.pritesh.bmi.renderer.XYSeriesRenderer;
import com.example.pritesh.bmi.adapter.WeightAdapter;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MyChart extends Fragment {

    private View mChart;
    private String[] mMonth = new String[] {
            "Mon", "Tues" , "Wed", "Thrs", "Fri", "Sat",
            "Sun", "Aug" , "Sep", "Oct", "Nov", "Dec"
    };

    ListView listView;

    Cursor c1;

    SharedPreferences list;

    public ArrayList<String> date1 = new ArrayList<String>();
    public ArrayList<String> time1 = new ArrayList<String>();
    public ArrayList<String> day = new ArrayList<String>();
    public ArrayList<String> weight = new ArrayList<String>();
    public ArrayList<String> idi = new ArrayList<String>();
    public ArrayList<String> diff = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        list = getContext().getSharedPreferences("MY", 0);
        return inflater.inflate(R.layout.chart, container, false);
    }


    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View v = getView();

        listView = (ListView)v.findViewById(R.id.lv);



        BmiDatabase bmiDatabase = new BmiDatabase(getContext());

        bmiDatabase.open();
      c1 =  bmiDatabase.getalldata();

        for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext()){

            date1.add(c1.getString(c1.getColumnIndex(bmiDatabase.KEY_DATE)));
            time1.add(c1.getString(c1.getColumnIndex(bmiDatabase.KEY_TIME)));
            day.add(c1.getString(c1.getColumnIndex(bmiDatabase.KEY_DAY)));
            weight.add(c1.getString(c1.getColumnIndex(bmiDatabase.KEY_WEIGHT)));
            idi.add(c1.getString(c1.getColumnIndex(bmiDatabase.KEY_BID)));
            diff.add(c1.getString(c1.getColumnIndex(bmiDatabase.KEY_DIFF)));

        }

        bmiDatabase.close();

        WeightAdapter weightAdapter = new WeightAdapter(getContext(),date1,time1,day,weight,diff);

        listView.setAdapter(weightAdapter);



               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                       Intent openlist = new Intent(getContext(), AddWeight.class);
                       openlist.putExtra("wight", weight.get(position));
                       openlist.putExtra("date", date1.get(position));
                       openlist.putExtra("time", time1.get(position));
                       openlist.putExtra("ids", idi.get(position));

                       list.edit().putString("key","mm").apply();

                       startActivity(openlist);

                   }
               });



// Getting reference to the button btn_chart

// Draw the Income vs Expense Chart
                openChart();

// Setting event click listener for the button btn_chart of the MainActivity layout

    }

    private void openChart(){
        int[] x = { 0,1,2,3,4,5,6,7 };


        int[] income = { 70,70,70,70,70,70,70,70};
        int[] expense = {100, 100, 100, 100, 100, 100, 100,100 };

        int[] min1 = {80,60,65,55 };
        int[] min = { 50  };

// Creating an XYSeries for Income
        XYSeries incomeSeries = new XYSeries("Income");
// Creating an XYSeries for Expense
        XYSeries expenseSeries = new XYSeries("Expense");
// Adding data to Income and Expense Series

        XYSeries min1Series = new XYSeries("MInn");

        XYSeries minSeries = new XYSeries("MIn");

        for(int i=0;i<12;i++){
            incomeSeries.add(i,70);
            expenseSeries.add(i,100);
            //     minSeries.add(i,min[i]);
        }

      //  for(int i=0;i<min1.length;i++) {

            min1Series.add(0,min1[0]);

        min1Series.add(2,min1[1]);

        min1Series.add(5,min1[2]);

        min1Series.add(6,min1[3]);


//        }


        for(int i=0;i<min.length;i++) {

            minSeries.add(i,min[i]);

        }

// Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
// Adding Income Series to the dataset
        dataset.addSeries(incomeSeries);
// Adding Expense Series to dataset
        dataset.addSeries(expenseSeries);

        dataset.addSeries(min1Series);


        dataset.addSeries(minSeries);


// Creating XYSeriesRenderer to customize incomeSeries
        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
        incomeRenderer.setColor(Color.CYAN); //color of the graph set to cyan
        incomeRenderer.setFillPoints(true);
        incomeRenderer.setLineWidth(2f);
        incomeRenderer.setDisplayChartValues(true);
//setting chart value distance
        incomeRenderer.setDisplayChartValuesDistance(5);
//setting line graph point style to circle
        incomeRenderer.setPointStyle(PointStyle.CIRCLE);
//setting stroke of the line chart to solid
        incomeRenderer.setStroke(BasicStroke.SOLID);

// Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
        expenseRenderer.setColor(Color.GREEN);
        expenseRenderer.setFillPoints(true);
        expenseRenderer.setLineWidth(2f);
        expenseRenderer.setDisplayChartValues(true);
//setting line graph point style to circle
        expenseRenderer.setPointStyle(PointStyle.SQUARE);
//setting stroke of the line chart to solid
        expenseRenderer.setStroke(BasicStroke.SOLID);



        XYSeriesRenderer min1Renderer = new XYSeriesRenderer();
        min1Renderer.setColor(Color.BLUE);
        min1Renderer.setFillPoints(true);
        min1Renderer.setLineWidth(2f);
        min1Renderer.setDisplayChartValues(true);
//setting line graph point style to circle
        min1Renderer.setPointStyle(PointStyle.DIAMOND);
//setting stroke of the line chart to solid
        min1Renderer.setStroke(BasicStroke.SOLID);



        XYSeriesRenderer minRenderer = new XYSeriesRenderer();
        minRenderer.setColor(Color.WHITE);
        minRenderer.setFillPoints(true);
        minRenderer.setLineWidth(2f);
        minRenderer.setDisplayChartValues(true);
//setting line graph point style to circle
        minRenderer.setPointStyle(PointStyle.POINT);
//setting stroke of the line chart to solid
        minRenderer.setStroke(BasicStroke.SOLID);


// Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("Weight Mgmt Chart");
        multiRenderer.setXTitle("month");
        multiRenderer.setYTitle("Weight in Kgs");

/***
 * Customizing graphs
 */
//setting text size of the title
        multiRenderer.setChartTitleTextSize(28);
//setting text size of the axis title
        multiRenderer.setAxisTitleTextSize(24);
//setting text size of the graph lable
        multiRenderer.setLabelsTextSize(24);
//setting zoom buttons visiblity
        multiRenderer.setZoomButtonsVisible(false);
//setting pan enablity which uses graph to move on both axis
        multiRenderer.setPanEnabled(false, false);
//setting click false on graph
        multiRenderer.setClickEnabled(false);
//setting zoom to false on both axis
        multiRenderer.setZoomEnabled(false, false);
//setting lines to display on y axis
        multiRenderer.setShowGridY(true);
//setting lines to display on x axis
        multiRenderer.setShowGridX(true);
//setting legend to fit the screen size
        multiRenderer.setFitLegend(true);
//setting displaying line on grid
        multiRenderer.setShowGrid(true);
//setting zoom to false
        multiRenderer.setZoomEnabled(false);
//setting external zoom functions to false
        multiRenderer.setExternalZoomEnabled(false);
//setting displaying lines on graph to be formatted(like using graphics)
        multiRenderer.setAntialiasing(true);
//setting to in scroll to false
        multiRenderer.setInScroll(false);
//setting to set legend height of the graph
        multiRenderer.setLegendHeight(30);
//setting x axis label align
        multiRenderer.setXLabelsAlign(Align.CENTER);
//setting y axis label to align
        multiRenderer.setYLabelsAlign(Align.LEFT);
//setting text style
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
//setting no of values to display in y axis
        multiRenderer.setYLabels(12);
// setting y axis max value, Since i'm using static values inside the graph so i'm setting y max value to 4000.
// if you use dynamic values then get the max y value and set here
        multiRenderer.setYAxisMax(160);
//setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setXAxisMin(-0.5);
//setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setXAxisMax(6);
//setting bar size or space between two bars
        //   multiRenderer.setBarSpacing(0.1);
//Setting background color of the graph to transparent
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
//Setting margin color of the graph to transparent
        multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setScale(2f);
//setting x axis point size
        multiRenderer.setPointSize(4f);
//setting the margin size for the graph in the order top, left, bottom, right
        multiRenderer.setMargins(new int[]{30, 30, 30, 30});

        for(int i=0; i< x.length;i++)
        {
            multiRenderer.addXTextLabel(i, mMonth[i]);
        }

      /*  for(int i=0; i< x.length;i++){
            multiRenderer.addXTextLabel(i, mDate[i]);
        }
*/



// Adding incomeRenderer and expenseRenderer to multipleRenderer
// Note: The order of adding dataseries to dataset and renderers to multipleRenderer
// should be same
        multiRenderer.addSeriesRenderer(incomeRenderer);

        multiRenderer.addSeriesRenderer(expenseRenderer);

        multiRenderer.addSeriesRenderer(min1Renderer);

        multiRenderer.addSeriesRenderer(minRenderer);


//this part is used to display graph on the xml
        LinearLayout chartContainer = (LinearLayout)getActivity().findViewById(R.id.chart);
//remove any views before u paint the chart
        chartContainer.removeAllViews();
//drawing bar chart
        mChart = ChartFactory.getLineChartView(getContext(), dataset, multiRenderer);
//adding the view to the linearlayout
        chartContainer.addView(mChart);

    }

}
