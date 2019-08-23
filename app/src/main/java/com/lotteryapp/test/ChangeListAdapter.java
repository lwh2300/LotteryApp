package com.lotteryapp.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class ChangeListAdapter extends ArrayAdapter {
    private List data;
    private Context context;
    private int resource;

    public ChangeListAdapter(Context context, int resource, List data) {
        super(context, resource);
        this.data=data;
        this.context=context;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View  view = LayoutInflater.from(context).inflate(resource,parent,false);
//        view.findViewById()
        return super.getView(position, convertView, parent);
    }
}
