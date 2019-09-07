package com.example.adil.navdrawertest;

import android.content.Intent;
import android.view.View;

import java.util.List;

/**
 * Created by Adil on 2017-11-21.
 */

public class Profile {


    String name;
    int points;
    List<Chore> chores;

    public Profile(String name){
        this.name = name;
        points = 0;

    }
 //   public Profile(String name, View picture){
  //      this.name = name;
   //     OnSetAvatarButton(picture);
   // }
    public void OnSetAvatarButton(View view) {
       // Intent intent = new Intent(getApplicationContext(), CreateProfile.class);
       // startActivityForResult (intent,0);
    }


    public String getProfileName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public List<Chore> getChores() {
        return chores;
    }

    public void setProfileName(String name) {
        this.name = name;
    }

    public void addPoints(int points) {
        this.points = this.points + points;
    }
    public void setPoints(int points){
        this.points = points;
    }

    public void setChores(List<Chore> chores) {
        this.chores = chores;
    }
    public void addAChore(Chore chore) {
        this.chores.add(chore);
    }


}
