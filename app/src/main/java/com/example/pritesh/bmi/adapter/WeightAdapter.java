package com.example.pritesh.bmi.adapter;

/**
 * Created by yashwant on 6/30/2016.
 */
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pritesh.bmi.R;

public class WeightAdapter extends BaseAdapter{
    Context ctx;
    LayoutInflater lInflater;
    // List<String> data;

    List<String> relist;
    List<String> rgelist;
    List<String> roelist;
    List<String> rdelist;
    List<String> rwelist;


   public WeightAdapter(Context context,List<String> relist,List<String> rgelist,List<String> roelist,List<String> rdelist,List<String> rwelist ) {
        ctx = context;
        this.relist = relist;
        this.rgelist = rgelist;

        this.roelist = roelist;
        this.rdelist = rdelist;
       this.rwelist = rwelist;



        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return relist.size();
    }

    @Override
    public Object getItem(int position) {
        return relist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.weightadpter, parent, false);
        }


        //  ((TextView) view.findViewById(R.id.cname)).setText(data.get(position));
        ((TextView) view.findViewById(R.id.date)).setText(relist.get(position));
        ((TextView) view.findViewById(R.id.time)).setText(rgelist.get(position));
        ((TextView) view.findViewById(R.id.day)).setText(roelist.get(position));
        ((TextView) view.findViewById(R.id.weight)).setText(rdelist.get(position)+"kg");
        ((TextView) view.findViewById(R.id.diff)).setText(rwelist.get(position));


        return view;
    }
}
