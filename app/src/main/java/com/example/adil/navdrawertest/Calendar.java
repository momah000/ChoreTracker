package com.example.adil.navdrawertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

// THE CALENDAR TAB

public class Calendar extends android.support.v4.app.Fragment {
    private static final String TAG = "Calendar";

    private Button btnTest ;

    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_tab2_fragment,container,false);
       // btnTest = (Button) view.findViewById(R.id.btnTEST2);


        return view;
    }

    public void btnPress(View view){
        Toast.makeText(getActivity(), "Testing button2click", Toast.LENGTH_SHORT);
    }

}