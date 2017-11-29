package com.example.adil.navdrawertest;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

// WHEN YOU PRESS  THE PLUS BUTTON YOU COME HERE!

public class CreateChore extends AppCompatActivity {

    TextView name;
    TextView requirements;
    TextView description;
    TextView points;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chore);

        name = (TextView) findViewById(R.id.editText);
        requirements = (TextView) findViewById(R.id.editText4);
        description = (TextView) findViewById(R.id.editText5);
        points = (TextView) findViewById(R.id.chorePointsID);


        Resources res = getResources();
        String [] names = res.getStringArray(R.array.spinnerItems);             // TRYING TO MAKE A DYNAMIC SPINNER
        ArrayList<String> profileNames = new ArrayList<>(Arrays.asList(names));

    }

    public void createChoreSubmit (View view){

        Chore chore = new Chore(name.getText().toString(),requirements.getText().toString(),description.getText().toString(), Integer.parseInt(points.getText().toString()) );

        MyDBHandler dbHandler = new MyDBHandler(this);

        dbHandler.addChore(chore);

        Intent i = getIntent();
        i.putExtra("choreName",name.getText().toString()); // sends it back to profile list
        setResult(RESULT_OK,i);
        finish();

    }
}
