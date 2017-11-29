package com.example.adil.navdrawertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adil.navdrawertest.R;

import org.w3c.dom.Text;

public class ChoreEditorActivity extends AppCompatActivity {
    Button completeChore;
    Button deleteChore;
    Button editChore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_editor);

        MyDBHandler dbHandler = new MyDBHandler(this);
       Chore chore = dbHandler.findChore(getIntent().getStringExtra("name"));



        TextView nameText = (TextView) findViewById(R.id.textView10);
        nameText.setText(chore.getChoreName());

         TextView requirementText = (TextView) findViewById(R.id.textView9);
         requirementText.setText(chore.getRequirements());

        TextView descriptionText = (TextView) findViewById(R.id.textView12);
        descriptionText.setText(chore.getDescription());

        //TextView pointsText = (TextView) findViewById(R.id.)








    }

    public void setCompleteChore(View view){

    }

    public void setDeleteChore(View view){

    }

    public void setEditChore(View view){

    }
}