package com.example.adil.navdrawertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Adil on 2017-11-21.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ChoreManagerDB.db";

    public static final String TABLE_PROFILES = "profiles";
    public static final String COLUMN_PROFILEID = "profileID";
    public static final String COLUMN_PROFILENAME = "profilename";
    public static final String COLUMN_POINTS = "points";
    public static final String COLUMN_PROFILECHORES = "chores";

    public static final String TABLE_CHORES = "chores";

    public static final String COLUMN_CHOREID = "choreID";
    public static final String COLUMN_CHORENAME = "chorename";
    public static final String COLUMN_CHOREPOINTS = "chorepoints";
    public static final String COLUMN_REQUIREMENTS = "requirements";
    public static final String COLUMN_DESCRIPTIONS = "descriptions";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_PERSONS = "persons";
    public static final String COLUMN_DEADLINE = "deadline";

    public static final String TABLE_IDS = "userChore";
    public static final String COLUMN_GROUPID = "connectID";
    public static final String COLUMN_USERID = "userID";
    public static final String COLUMN_CHOREsID = "choresID";

    public static int counter =0;


    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override   
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROFILES_TABLE = "CREATE TABLE " +
                TABLE_PROFILES + "("
                + COLUMN_PROFILEID + " INTEGER," + COLUMN_PROFILENAME
                + " TEXT PRIMARY KEY," + COLUMN_POINTS + " INTEGER, " + COLUMN_PROFILECHORES + " TEXT" + ")";

        String CREATE_CHORES_TABLE = "CREATE TABLE " + TABLE_CHORES + "(" + COLUMN_CHOREID + " INTEGER,"
                + COLUMN_CHORENAME + " TEXT PRIMARY KEY," + COLUMN_REQUIREMENTS + " TEXT," + COLUMN_DESCRIPTIONS + " TEXT," + COLUMN_CHOREPOINTS + " INTEGER" + ")";

        String CREATE_CONNECT_TABLE = "CREATE TABLE " + TABLE_IDS + "(" + COLUMN_GROUPID+ " INTEGER,"+ COLUMN_USERID + " INTEGER," + COLUMN_CHOREsID + " INTEGER" + ")";
        db.execSQL(CREATE_PROFILES_TABLE);
        db.execSQL(CREATE_CHORES_TABLE);
        db.execSQL(CREATE_CONNECT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHORES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IDS);
        onCreate(db);
    }

    public boolean addProfile (Profile profile){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PROFILENAME,profile.getProfileName());
        values.put(COLUMN_POINTS,profile.getPoints());

        long answer = db.insert(TABLE_PROFILES,null,values);
        db.close();

        if (answer == 1){
            return false;
        }
        else{
            return true;
        }
    }

    public void addChore(Chore chore, ArrayList<String> selectedProfiles){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_CHORENAME, chore.getChoreName());
        values.put(COLUMN_REQUIREMENTS, chore.getRequirements()); // filling out 1 row in the chore table
        values.put(COLUMN_DESCRIPTIONS, chore.getDescription());
        values.put(COLUMN_CHOREPOINTS, chore.getPoints());


        for(int i=0; i<selectedProfiles.size();i++) { // THIS IS FOR THE CONNECTING TABLE WITH ALL THE ID'S
            System.out.println(selectedProfiles.get(i));
            String queryProfile = "Select * FROM " + TABLE_PROFILES + " WHERE " + COLUMN_PROFILENAME + " =\"" + selectedProfiles.get(i) + "\""; // GET ID FROM PROFIEL
            Cursor cursor1 = db.rawQuery(queryProfile,null);

            ContentValues connect = new ContentValues();
            connect.put(COLUMN_GROUPID, counter);                 // THIS WILL GROUP a cHOER WITH MULTIPLE USERs
            connect.put(COLUMN_USERID, selectedProfiles.get(i));  // ID OF THE USER
            connect.put(COLUMN_CHOREsID, chore.getChoreName()); // ID OF CHORE
            db.insert(TABLE_IDS,null,connect);
        }

       db.insert(TABLE_CHORES,null,values);
        counter++;
        db.close();
    }




    public Cursor getAllProfiles(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_PROFILES,null);
        return res;
    }
    public Cursor getAllChores(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_CHORES,null);
        return res;
    }
    public ArrayList<String> getProfileChores (String profileName){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<String> profileList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_IDS + " WHERE " + COLUMN_USERID + " =\"" + profileName + "\"",null);

        while(cursor.moveToNext()){
            profileList.add(cursor.getString(2));
        }

        return profileList;

    }
    public void assignChoreToProfiles(String pname, String cname){


    }

    public Chore findChore (String choreName){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM " + TABLE_CHORES + " WHERE " + COLUMN_CHORENAME + " =\"" + choreName + "\"";
        Cursor cursor = db.rawQuery(query,null);

        Chore chore = new Chore();

        if(cursor.moveToFirst())
        {
            //chore.set(Integer.parseInt(cursor.getString(0)));
            chore.setChoreName(cursor.getString(1));
            chore.setRequirements(cursor.getString(2));
            chore.setDescription(cursor.getString(3));
            chore.setPoints(Integer.parseInt(cursor.getString(4)));

            cursor.close();
        }
        else{
            chore=null;
        }
        db.close();
        return chore;
    }

    public Profile findProfile (String profileName){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM " + TABLE_PROFILES + " WHERE " + COLUMN_PROFILENAME + " =\"" + profileName + "\"";
        Cursor cursor = db.rawQuery(query,null);

        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_PROFILES,null);

        Profile profile = new Profile(profileName);



        if(cursor.moveToFirst())
        {
            profile.setProfileName(cursor.getString(1));
            profile.setPoints(Integer.parseInt(cursor.getString(2)));


            cursor.close();
        }
        else{
            profile=null;
        }
        db.close();
        return profile;
    }

    public void removeChore(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        System.out.println ( " THE cHORE TO REMOVE IS " + name);

        String query = "Select * FROM " + TABLE_CHORES + " WHERE " + COLUMN_CHORENAME + " =\"" + name + "\"";
        String query1 = "Select * FROM " + TABLE_IDS + " WHERE " + COLUMN_CHOREsID + " =\"" + name + "\"";

        Cursor cursor =db.rawQuery( query, null);
        Cursor cursor1=db.rawQuery(query1,null);
        if(cursor.moveToNext()){
            db.delete(TABLE_CHORES,COLUMN_CHORENAME +  " = ?", new String[] {name});
        }
        while (cursor1.moveToNext()){
            db.delete(TABLE_IDS,COLUMN_CHOREsID+" = ?", new String[]{name});
        }


        db.close();


        return ;
    }

    public void completeChore(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        System.out.println ( " THE cHORE TO REMOVE IS " + name);

        String query = "Select * FROM " + TABLE_CHORES + " WHERE " + COLUMN_CHORENAME + " =\"" + name + "\"";
        String query1 = "Select * FROM " + TABLE_IDS + " WHERE " + COLUMN_CHOREsID + " =\"" + name + "\"";

        Cursor cursor =db.rawQuery( query, null);
        Cursor cursor1=db.rawQuery(query1,null);
        int pointsObtained=0;
        if(cursor.moveToNext()){
            pointsObtained = Integer.parseInt(cursor.getString(4));
            db.delete(TABLE_CHORES,COLUMN_CHORENAME +  " = ?", new String[] {name});
        }
        while (cursor1.moveToNext()){
            String query2 = "Select * FROM " + TABLE_PROFILES + " WHERE " + COLUMN_PROFILENAME + " =\"" + cursor1.getString(1) + "\"";
            Cursor cursor2=db.rawQuery(query2,null);
            cursor2.moveToFirst();
            System.out.println("THIS IS IN CURSOR2 " + cursor2.getString(1));
            int currentPoints = Integer.parseInt(cursor2.getString(2));
            ContentValues values = new ContentValues();
            values.put(COLUMN_PROFILEID, cursor2.getString(0));
            values.put(COLUMN_PROFILENAME, cursor2.getString(1));
            int totalpoints = currentPoints+pointsObtained;
            values.put(COLUMN_POINTS, totalpoints);

            db.update(TABLE_PROFILES, values,COLUMN_PROFILENAME+ "=?", new String[]{cursor1.getString(1)});

            db.delete(TABLE_IDS,COLUMN_CHOREsID+" = ?", new String[]{name});
        }




        db.close();
        return;

    }

    public void removeAllProfiles(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =db.rawQuery( "Select * FROM " + TABLE_PROFILES, null);

        while(cursor.moveToNext()){
            String idStr = cursor.getString(0);
            Log.e("THiswhatidSTR printS:" , idStr);
            db.delete(TABLE_PROFILES,COLUMN_PROFILEID, null);

        }
        db.close();

        return ;
    }

    public void removeAllChores(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =db.rawQuery( "Select * FROM " + TABLE_CHORES, null);

        while(cursor.moveToNext()){
            String idStr = cursor.getString(0);
            Log.e("THiswhatidSTR printS:" , idStr);
            db.delete(TABLE_CHORES,COLUMN_CHOREID, null);

        }
        db.close();

        return ;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_CHORES,null);
        return res;
    }

    public void updateChore(String name, String requirement, String description, String points){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHORENAME, name);
        values.put(COLUMN_REQUIREMENTS, requirement );
        values.put(COLUMN_DESCRIPTIONS, description);
        values.put(COLUMN_CHOREPOINTS, points);

        db.update(TABLE_CHORES, values,COLUMN_CHORENAME+ "=?", new String[]{name});
    }




}

