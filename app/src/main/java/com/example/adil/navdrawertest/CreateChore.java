package com.example.adil.navdrawertest;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// WHEN YOU PRESS  THE PLUS BUTTON YOU COME HERE!

public class CreateChore extends AppCompatActivity {

    TextView name;
    TextView requirements;
    TextView description;
    TextView points;
    ListView listView;
    ArrayList<String> selectedProfiles;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chore);

        listView = findViewById(R.id.choosePersonID);
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);
        listView.setItemsCanFocus(false);

        MyDBHandler dbHandler = new MyDBHandler(this);

        selectedProfiles = new ArrayList<>();


        final ArrayList<String> profileList = getAllProfileNames();

        choosePersonAdapter adapter = new choosePersonAdapter(this, profileList);
        listView.setAdapter(adapter);

        name = (TextView) findViewById(R.id.editText);
        requirements = (TextView) findViewById(R.id.editText4);
        description = (TextView) findViewById(R.id.editText5);
        points = (TextView) findViewById(R.id.chorePointsID);


        Resources res = getResources();
        String [] names = res.getStringArray(R.array.spinnerItems);             // TRYING TO MAKE A DYNAMIC SPINNER
        ArrayList<String> profileNames = new ArrayList<>(Arrays.asList(names));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                selectedProfiles.add(profileList.get(position));
            }
        });

    }

    public void createChoreSubmit (View view){

        Chore chore = new Chore(name.getText().toString(),requirements.getText().toString(),description.getText().toString(), Integer.parseInt(points.getText().toString()) );

        MyDBHandler dbHandler = new MyDBHandler(this);

        for(int i=0; i< selectedProfiles.size();i++) {
            System.out.println(selectedProfiles.get(i));
        }
        System.out.println("END OF NAMES FROM PROFILELIST");

        dbHandler.addChore(chore, selectedProfiles);

        Intent i = getIntent();
        System.out.println("THE name we are sending back is: " + name.getText().toString());
        i.putExtra("choreName",name.getText().toString()); // sends it back to chore list
        setResult(RESULT_OK,i);
        finish();

    }

    public ArrayList<String> getAllProfileNames(){
        MyDBHandler dbHandler = new MyDBHandler(this);

        ArrayList<String> profileList = new ArrayList<>();

        Cursor cursor = dbHandler.getAllProfiles();

        while(cursor.moveToNext()){
            profileList.add(cursor.getString(1));
        }
        return profileList;
    }
}
