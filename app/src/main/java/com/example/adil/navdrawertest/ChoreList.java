package com.example.adil.navdrawertest;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Adil on 2017-11-09.
 */

//  THIS IS THE CHORE LIST TAB

public class ChoreList extends android.support.v4.app.Fragment {
    private static final String TAG = "ChoreList";

    private Button btnTest ;
    private ListView listView;
    ArrayList<String> choreList;

    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_tab1_fragment,container,false);
        choreList = new ArrayList<>();
        updateChores();


        listView = (ListView) view.findViewById(R.id.listTEST);

        ChoreCustomAdapter adapter = new ChoreCustomAdapter(getContext(), choreList);
        listView.setAdapter(adapter);

        MyDBHandler myDBHandler = new MyDBHandler(getContext().getApplicationContext());


        setHasOptionsMenu(true);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                Intent editorLaunchInterest = new Intent(getActivity().getApplicationContext(), ChoreEditorActivity.class); // Goes to another page , goes to another intent
                //editorLaunchInterest.putExtra("position",position);
               editorLaunchInterest.putExtra("name",choreList.get(position));
                startActivityForResult(editorLaunchInterest, 3);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("So did we get into the fragment part tho??");
        if(requestCode==1 && resultCode==RESULT_OK){
            System.out.println("This should not work when completing chore");
            addChore(data.getStringExtra("choreName"));
        }

        MyDBHandler dbHandler = new MyDBHandler(getContext());



    }


    public void btnPress(View view){
        Toast.makeText(getActivity(), "Testing button1click", Toast.LENGTH_SHORT);
    }

    public void addChore(String choreName){
        choreList.add(choreName);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(getContext(), choreList);
        listView.setAdapter(adapter);
    }


    public void updateChores(){
        MyDBHandler dbHandler = new MyDBHandler(getContext().getApplicationContext());
        Cursor res = dbHandler.getAllChores();

        while(res.moveToNext()){
            choreList.add(res.getString(1));
            //System.out.println(res.getString(1));
        }
    }
    public void deleteAllChores(){


    }









    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.   // THIS ADDS PLUS BUTTON
        inflater.inflate(R.menu.main, menu);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.             // ACTION WHEN PLUS BUTTON IS PRESSED
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(getContext(),CreateChore.class);
            startActivityForResult(myIntent,1);

        }

        return super.onOptionsItemSelected(item);
    }

}