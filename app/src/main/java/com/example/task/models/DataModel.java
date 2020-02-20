package com.example.task.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity

public class DataModel {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String name;
    @ColumnInfo
    private int age;
    @ColumnInfo
    private String job;
    @ColumnInfo
    private String gender;


    public DataModel() {
    }

    @Ignore
    public DataModel(String name, int age, String job, String gender) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
