package com.example.adil.navdrawertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
   // public static final String COLUMN_POINTS = "points";
   // public static final String COLUMN_CHORES = "chores";

    public static final String TABLE_CHORES = "chores";

    public static final String COLUMN_CHOREID = "choreID";
    public static final String COLUMN_CHORENAME = "chorename";
    public static final String COLUMN_CHOREPOINTS = "chorepoints";
    public static final String COLUMN_REQUIREMENTS = "requirements";
    public static final String COLUMN_DESCRIPTIONS = "descriptions";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_PERSONS = "persons";
    public static final String COLUMN_DEADLINE = "deadline";


    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROFILES_TABLE = "CREATE TABLE " +
                TABLE_PROFILES + "("
                + COLUMN_PROFILEID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PROFILENAME
                + " TEXT" +  ")";
        String CREATE_CHORES_TABLE = "CREATE TABLE " + TABLE_CHORES + "(" + COLUMN_CHOREID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CHORENAME + " TEXT," + COLUMN_REQUIREMENTS + " TEXT," + COLUMN_DESCRIPTIONS + " TEXT," + COLUMN_CHOREPOINTS + " INTEGER" + ")";
        db.execSQL(CREATE_PROFILES_TABLE);
        db.execSQL(CREATE_CHORES_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHORES);
        onCreate(db);
    }

    public boolean addProfile (Profile profile){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PROFILENAME,profile.getProfileName());
       // db.insert(TABLE_PROFILES,null,values);

        long answer = db.insert(TABLE_PROFILES,null,values);
       // Log.d(TAG,"addData: Adding " + profile.getProfileName() + " TO " + TABLE_PROFILES);

        db.close();

        if (answer == 1){
            return false;
        }
        else{
            return true;
        }

    }

    public void addChore(Chore chore){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_CHORENAME, chore.getChoreName());
        values.put(COLUMN_REQUIREMENTS, chore.getRequirements());
        values.put(COLUMN_DESCRIPTIONS, chore.getDescription());
        values.put(COLUMN_CHOREPOINTS, chore.getPoints());


        // db.insert(TABLE_PROFILES,null,values);

       db.insert(TABLE_CHORES,null,values);
        // Log.d(TAG,"addData: Adding " + profile.getProfileName() + " TO " + TABLE_PROFILES);

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

    public void removeAllProfiles(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =db.rawQuery( "Select * FROM " + TABLE_PROFILES, null);

        while(cursor.moveToNext()){
            String idStr = cursor.getString(0);
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
            db.delete(TABLE_CHORES,COLUMN_CHOREID, null);

        }
        db.close();

        return ;
    }







}

