package com.example.task.views.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.task.Base.BaseActivity;
import com.example.task.R;
import com.example.task.databinding.ActivityMainBinding;
import com.example.task.models.DataModel;
import com.example.task.viewModels.main.mainViewModel;
import com.example.task.views.results.ResultsActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, mainViewModel> {


    public static final String charRegex = "[a-zA-Z ]+";
    public static DataModel dataModel = new DataModel();
    boolean errorInAddDataToDataBase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideProgress();
        watchAgeLabel();
        watchGenderLabel();
        viewClicks();
    }

    private void viewClicks() {
        databinding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        databinding.resultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showResults();

            }
        });
    }


    private void save() {
        showProgress();
        if (!databinding.name.getText().toString().trim().isEmpty()
                && !databinding.jobTitle.getText().toString().trim().isEmpty()
                && !databinding.gender.getText().toString().trim().isEmpty()
                && !databinding.age.getText().toString().trim().isEmpty())  // to validate if there is empty or not
        {
            if (!databinding.gender.getText().toString().trim().matches(charRegex) ||
                    databinding.age.getText().toString().trim().matches(charRegex))  //to validate if there is apply regex or not
            {
                hideProgress();
                showToast(R.string.fixErrors, this);

            } else {
                //set data in the model to send it to view model
                dataModel.setName(databinding.name.getText().toString().trim());
                dataModel.setJob(databinding.jobTitle.getText().toString().trim());
                dataModel.setGender(databinding.gender.getText().toString().trim());
                dataModel.setAge(Integer.parseInt(databinding.age.getText().toString().trim()));
                viewModel = new mainViewModel(getApplication());
                viewModel.setData(dataModel);
                observeErrorLiveData(); // to check if there is error in add data to database or not

                if (errorInAddDataToDataBase) {
                    hideProgress();
                    showToast(R.string.there_is_error_please_try_again, this);


                } else {
                    hideProgress();
                    showToast(R.string.data_added_succesfully, this);
                }

            }
        } else {
            hideProgress();
            showMessage(R.string.dataError, R.string.ok);
        }
    }


    // to validate that user didn't add characters in age field
    private void watchAgeLabel() {
        databinding.age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().matches(charRegex)) {
                    databinding.age.setError("Age accept only numbers");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    // to validate that user didn't add numbers in gender field
    private void watchGenderLabel() {
        databinding.gender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().matches(charRegex)) {
                    databinding.gender.setError("Gender doesn't accept numbers");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void observeErrorLiveData() {
        viewModel.mainError.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                errorInAddDataToDataBase = true;
                showToast(s, getApplicationContext());
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected mainViewModel getViewModel() {
        return new ViewModelProvider(this).get(mainViewModel.class);
    }

    public void hideProgress() {
        databinding.progress.setVisibility(View.GONE);
    }

    public void showProgress() {
        databinding.progress.setVisibility(View.VISIBLE);
    }

    public void showResults() {
        startActivity(new Intent(this, ResultsActivity.class));
    }
}
