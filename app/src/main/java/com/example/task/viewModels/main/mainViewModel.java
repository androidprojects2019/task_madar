package com.example.task.viewModels.main;

import android.app.Application;

import com.example.task.Repositories.main.mainRepo;
import com.example.task.models.DataModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class mainViewModel extends AndroidViewModel {

    public static MutableLiveData<String> mainError = new MutableLiveData<>();

    public mainViewModel(@NonNull Application application) {
        super(application);
        mainError = mainRepo.mainError;
    }

    public void setData(DataModel data) {
        mainRepo.addData(data, getApplication());
    }
}
