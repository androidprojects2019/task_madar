package com.example.task.viewModels.results;

import android.app.Application;
import android.content.Context;

import com.example.task.Repositories.results.resultsRepo;
import com.example.task.models.DataModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class resultsViewModel extends AndroidViewModel {
    public static MutableLiveData<List<DataModel>> results = new MutableLiveData<>();
    public static MutableLiveData<String> resultsError = new MutableLiveData<>();

    public resultsViewModel(@NonNull Application application) {
        super(application);
        results = resultsRepo.results;
        resultsError = resultsRepo.resultsError;
    }

    public void getData() {
        resultsRepo.getData(getApplication());
    }
}
