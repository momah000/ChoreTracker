package com.example.adil.navdrawertest;

import java.util.List;

/**
 * Created by Adil on 2017-11-21.
 */

public class Chore {
    String name;
    String requirements;
    String description;
    int points;
    String status;
    int frequency;
    String notes;
    List<Profile> person;

    public Chore(){
        this.name = " ";
        this.requirements = " ";
        this.description = " ";
        this.points = 0;
    }

    public Chore (String name, String requirements, String description, int points){
        this.name = name;
        this.requirements = requirements;
        this.description = description;
        this.points = points;
    }

    public String getChoreName(){ return name;}

    public void setChoreName(String name) {this.name = name;}


    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {this.description= description;}

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Profile> getPerson() {
        return person;
    }

    public void setPerson(List<Profile> person) {
        this.person = person;
    }

}
