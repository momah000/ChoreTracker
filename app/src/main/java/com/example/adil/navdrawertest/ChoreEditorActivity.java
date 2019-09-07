package com.example.adil.navdrawertest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adil.navdrawertest.R;

import org.w3c.dom.Text;

public class ChoreEditorActivity extends AppCompatActivity {
    Button completeChore;
    Button deleteChore;
    Button editChore;
    EditText nameText;
    EditText requirementText;
    EditText descriptionText;
    EditText pointsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_editor);

        MyDBHandler dbHandler = new MyDBHandler(this);
       Chore chore = dbHandler.findChore(getIntent().getStringExtra("name"));

        System.out.println("THIS IS ON CREATE OF CHORE EDITOR ACTVITY");

        nameText = (EditText) findViewById(R.id.editText7);
        nameText.setText(chore.getChoreName());

        requirementText = (EditText) findViewById(R.id.requirements);
        requirementText.setText(chore.getRequirements());

        descriptionText = (EditText) findViewById(R.id.description);
        descriptionText.setText(chore.getDescription());

        pointsText = (EditText) findViewById(R.id.pointsID);
        pointsText.setText(String.valueOf(chore.getPoints()));

    }

    public void setCompleteChore(View view){
            MyDBHandler dbHandler = new MyDBHandler(this);

            //Chore chore = dbHandler.findChore(nameText.toString());


            dbHandler.completeChore(nameText.getText().toString());
            Intent goToChoreList = new Intent(ChoreEditorActivity.this,MainActivity.class);
            startActivity(goToChoreList);


    }

    public void setDeleteChore(View view){

        MyDBHandler dbHandler = new MyDBHandler(this);

        dbHandler.removeChore(nameText.getText().toString());

        Intent goToChoreList = new Intent(ChoreEditorActivity.this,MainActivity.class);
        startActivity(goToChoreList);

    }

    public void realEditChore(View view){
        MyDBHandler dbHandler = new MyDBHandler(this);

        System.out.println("THIS IS THE EDITED FIELDS");
        System.out.println(nameText.getText().toString());
        System.out.println(requirementText.getText().toString());
        System.out.println(descriptionText.getText().toString());

        dbHandler.updateChore(nameText.getText().toString(),requirementText.getText().toString(), descriptionText.getText().toString(), pointsText.getText().toString());

        Intent goToChoreList = new Intent(ChoreEditorActivity.this,MainActivity.class);
        startActivity(goToChoreList);

    }

    public void viewAll(View view){
        MyDBHandler dbHandler = new MyDBHandler(this);
        Cursor res = dbHandler.getAllData();
        if(res.getCount() == 0){
            showMessage("Eror: " , "Nothin in database");
            return;
        }
        else{
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
                buffer.append ("ID : " + res.getString(0) + "\n");
                buffer.append ("ChoreName : " + res.getString(1)+ "\n");
                buffer.append ("Chore Requirements : " + res.getString(2) + "\n");
                buffer.append ("Chore Description : " + res.getString(3) + "\n");
                buffer.append ("Chore Points : " + res.getString(4) + "\n");
            }

            showMessage("Data: ",buffer.toString());
        }
    }

    public void showMessage(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}