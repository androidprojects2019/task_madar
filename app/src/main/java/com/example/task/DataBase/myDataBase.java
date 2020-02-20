package com.example.task.DataBase;

import android.content.Context;

import com.example.task.DataBase.daos.userDao;
import com.example.task.models.DataModel;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataModel.class}, version = 1, exportSchema = false)

public abstract class myDataBase extends RoomDatabase {
    private static myDataBase myDataBase;
    private static final String DB_NAME = "UserDataBase";

    public abstract userDao userDao();

    public myDataBase() {
    }

    public static myDataBase getInstance(Context context) {

        if (myDataBase == null) {
            myDataBase = Room.databaseBuilder(context, myDataBase.class,
                    DB_NAME).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return myDataBase;
    }
}