package com.example.adil.navdrawertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Adil on 2017-11-29.
 */

public class ProfileEditorAdapter extends ArrayAdapter {

    private final Context context;
    private ArrayList<String> myChores;

    public ProfileEditorAdapter(Context context, ArrayList<String> choreList) {
        super(context, R.layout.chore_item_layout, choreList);
        this.context = context;
        this.myChores = choreList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chore_item_layout, parent, false);

        MyDBHandler dbHandler = new MyDBHandler(getContext());



        TextView name = (TextView) rowView.findViewById(R.id.itemName);

        Chore chore = dbHandler.findChore(myChores.get(position));


        ((TextView) rowView.findViewById(R.id.itemName)).setText(chore.getChoreName());
       // TextView choreDescriptionTextField = (TextView) rowView.findViewById(R.id.itemDescription);
       // choreDescriptionTextField.setText(chore.getDescription());
       // ImageView choreImage = (ImageView) rowView.findViewById(R.id.icon);


      //  if (position%2 == 0)
      //  {
      //      choreImage.setBackgroundResource(R.mipmap.ic_launcher);
      //  }
        return rowView;
    }
}
