package com.example.task.views.results;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.task.Base.BaseActivity;
import com.example.task.R;
import com.example.task.databinding.ActivityResultsBinding;
import com.example.task.models.DataModel;
import com.example.task.viewModels.results.resultsViewModel;

import java.util.List;

public class ResultsActivity extends BaseActivity<ActivityResultsBinding, resultsViewModel> {

    ResultsAdapter adapter;
    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new resultsViewModel(getApplication());
        viewModel.getData();
        initView();
        ObserveResults();
    }

    private void initView() {
        databinding.emptyText.setVisibility(View.GONE); // to hide the empty till know if there is data or not
    }


    //this function to observe on live data if results return data then the request success
    //if results error return result then request failed
    private void ObserveResults() {
        viewModel.results.observe(this, new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> dataModels) {
                hideProgress();
                initRecycler(dataModels);
                if (dataModels.size() == 0) {
                    databinding.emptyText.setVisibility(View.VISIBLE); // to make empty text visible if there is no data
                }
            }
        });

        viewModel.resultsError.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                hideProgress();
                showToast(s, getApplicationContext());
            }
        });
    }

    private void initRecycler(List<DataModel> dataModels) {
        adapter = new ResultsAdapter(dataModels);
        manager = new LinearLayoutManager(getApplicationContext());
        databinding.recycler.setAdapter(adapter);
        databinding.recycler.setLayoutManager(manager);
    }

    @Override
    public int getLayoutId() {
        return (R.layout.activity_results);
    }

    @Override
    protected resultsViewModel getViewModel() {
        return new ViewModelProvider(this).get(resultsViewModel.class);
    }

    public void hideProgress() {
        databinding.progress.setVisibility(View.INVISIBLE);
    }


}
