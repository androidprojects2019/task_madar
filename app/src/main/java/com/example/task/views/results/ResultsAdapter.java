package com.example.task.views.results;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.models.DataModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.userViewHolder> {

    List<DataModel> dataModels;

    public ResultsAdapter(List<DataModel> dataModels) {
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_results, parent, false);
        return new userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {
        DataModel item = dataModels.get(position);
        holder.name.setText(item.getName());
        holder.gender.setText(item.getGender());
        holder.age.setText((Integer.toString(item.getAge())));
        holder.job.setText(item.getJob());
    }

    @Override
    public int getItemCount() {
        if (dataModels != null) return dataModels.size();
        return 0;

    }

    public class userViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView age;
        TextView job;
        TextView gender;

        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.result_name);
            age = itemView.findViewById(R.id.result_age);
            job = itemView.findViewById(R.id.result_job);
            gender = itemView.findViewById(R.id.result_gender);
        }
    }
}
