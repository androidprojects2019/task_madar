package com.example.task.Repositories.main;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.task.DataBase.myDataBase;
import com.example.task.models.DataModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class mainRepo {
    public static MutableLiveData<String> mainError = new MutableLiveData<>();

    public static void addData(DataModel model, Context context) {
        myDataBase
                .getInstance(context)
                .userDao()
                .addData(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mainError.setValue(e.getLocalizedMessage());
                    }
                });

    }


}
