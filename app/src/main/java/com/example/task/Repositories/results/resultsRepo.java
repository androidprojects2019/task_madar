package com.example.task.Repositories.results;

import android.content.Context;

import com.example.task.DataBase.myDataBase;
import com.example.task.models.DataModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class resultsRepo {
    public static MutableLiveData<List<DataModel>> results = new MutableLiveData<>();
    public static MutableLiveData<String> resultsError = new MutableLiveData<>();

    public static void getData(Context context) {
        myDataBase
                .getInstance(context)
                .userDao()
                .getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<DataModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<DataModel> dataModels) {
                        results.setValue(dataModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        resultsError.setValue(e.getLocalizedMessage());
                    }
                });
    }

}
