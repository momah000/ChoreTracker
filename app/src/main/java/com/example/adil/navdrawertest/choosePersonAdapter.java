package com.example.adil.navdrawertest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adil on 2017-11-29.
 */

public class choosePersonAdapter extends ArrayAdapter {

    private final Context context;
    private ArrayList<String> profileList;

    public choosePersonAdapter(Context context, ArrayList<String> choreList) {
        super(context, R.layout.choose_person_layout, choreList);
        this.context = context;
        this.profileList = choreList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.choose_person_layout, parent, false);

        MyDBHandler dbHandler = new MyDBHandler(getContext());


        TextView name = (TextView) rowView.findViewById(R.id.choosePersonID);
        ((TextView) rowView.findViewById(R.id.choosePersonID)).setText(profileList.get(position));


        return rowView;
    }
}
