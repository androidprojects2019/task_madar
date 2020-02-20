package com.example.task.DataBase.daos;

import com.example.task.models.DataModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface userDao {

    @Insert()
    Completable addData(DataModel model);

    @Query("select * from DataModel")
    Single<List<DataModel>> getData();

}
