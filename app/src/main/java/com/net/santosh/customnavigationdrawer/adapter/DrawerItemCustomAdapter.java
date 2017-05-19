package com.net.santosh.customnavigationdrawer.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.net.santosh.customnavigationdrawer.R;
import com.net.santosh.customnavigationdrawer.model.NavDrawerModel;

/**
 * Created by anupamchugh on 10/12/15.
 */
public class DrawerItemCustomAdapter extends ArrayAdapter<NavDrawerModel> {

    Context mContext;
    int layoutResourceId;
    NavDrawerModel data[] = null;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, NavDrawerModel[] data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);
        TextView textViewSubName = (TextView) listItem.findViewById(R.id.textViewSubName);

        NavDrawerModel folder = data[position];


        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);
        textViewSubName.setText(folder.subName);

        return listItem;
    }
}

