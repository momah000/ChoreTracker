package com.example.adil.navdrawertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileEditorActivity extends AppCompatActivity {

    ListView listView;
    TextView name;
    TextView points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);
        MyDBHandler dbHandler = new MyDBHandler(this);

        listView = (ListView) findViewById(R.id.choresForPersonID) ;
        name = (TextView) findViewById(R.id.name);
        points = (TextView) findViewById(R.id.points);

        String profileName = getIntent().getStringExtra("profileName");

        Profile profile = dbHandler.findProfile(profileName);

        name.setText(profile.getProfileName());
        points.setText(String.valueOf(profile.getPoints()));





        ArrayList<String> choreList = dbHandler.getProfileChores(profileName);

        for(int i=0; i<choreList.size();i++){
            System.out.println(choreList.get(i));
        }

        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this, choreList);
        listView.setAdapter(adapter);


    }


}
