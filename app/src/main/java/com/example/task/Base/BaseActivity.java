package com.example.task.Base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

public abstract class BaseActivity<DB extends ViewDataBinding,
        vm extends ViewModel> extends AppCompatActivity {

    protected vm viewModel;
    protected DB databinding;

    public AppCompatActivity activity;


    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDataBinding();
        viewModel = getViewModel();

    }

    protected abstract vm getViewModel();

    public void initializeDataBinding() {
        databinding =
                DataBindingUtil.setContentView(this,
                        getLayoutId());
    }


    public AlertDialog showMessage(int message, int posActionName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.show();
    }

    public void showToast(int message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public void showToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
