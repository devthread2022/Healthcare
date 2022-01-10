package com.jvt.devthread.healthcare.Activities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.healthcare.Activities.Common.Common;
import com.jvt.devthread.healthcare.Activities.Model.TimeModel;
import com.jvt.devthread.healthcare.R;

import java.util.List;

public class AvailableTimeAdapter extends RecyclerView.Adapter<AvailableTimeAdapter.ViewHolder> {
    public Context context;
    private List<TimeModel> timeModelList;

    public AvailableTimeAdapter(Context context, List<TimeModel> timeModelList) {
        this.context = context;
        this.timeModelList = timeModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimeModel timeModel = timeModelList.get(position);
        holder.time.setText(timeModel.getTime());
        holder.itemView.setOnClickListener(view -> {
            Common.sTime = timeModelList.get(position).getTime();
        });
    }

    @Override
    public int getItemCount() {
        return timeModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
        }
    }
}
