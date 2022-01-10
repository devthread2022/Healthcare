package com.jvt.devthread.healthcare.Activities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.healthcare.Activities.Model.PatientStoriesModel;
import com.jvt.devthread.healthcare.R;

import java.util.List;

public class PatientStoriesAdapter extends RecyclerView.Adapter<PatientStoriesAdapter.ViewHolder> {
    public Context context;
    private List<PatientStoriesModel> patientStoriesModelList;

    public PatientStoriesAdapter(Context context, List<PatientStoriesModel> patientStoriesModelList) {
        this.context = context;
        this.patientStoriesModelList = patientStoriesModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_stories_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PatientStoriesModel patientStoriesModel = patientStoriesModelList.get(position);
        holder.name.setText(patientStoriesModel.getName());
        holder.date.setText(patientStoriesModel.getDate());
        holder.reason.setText(patientStoriesModel.getReason());
        holder.feedback.setText(patientStoriesModel.getFeedback());

    }

    @Override
    public int getItemCount() {
        return patientStoriesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, reason, feedback;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.patient_name);
            date = itemView.findViewById(R.id.date);
            reason = itemView.findViewById(R.id.reason);
            feedback = itemView.findViewById(R.id.feedback);
        }
    }
}
