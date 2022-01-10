package com.jvt.devthread.healthcare.Activities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.healthcare.Activities.Common.Common;
import com.jvt.devthread.healthcare.Activities.Model.DateModel;
import com.jvt.devthread.healthcare.R;

import java.util.List;

public class AvailableDatesAdapter extends RecyclerView.Adapter<AvailableDatesAdapter.ViewHolder> {
    public Context context;
    private List<DateModel> dateModelList;

    public AvailableDatesAdapter(Context context, List<DateModel> dateModelList) {
        this.context = context;
        this.dateModelList = dateModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DateModel dateModel = dateModelList.get(position);
        holder.date.setText(dateModel.getDayName()+","+dateModel.getDate());
        holder.slot.setText(String.valueOf(dateModel.getAvailableSlot()));
        holder.itemView.setOnClickListener(view -> {
            Common.sDate = dateModelList.get(position).getDayName()+","+dateModelList.get(position).getDate();
        });
    }

    @Override
    public int getItemCount() {
        return dateModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date, slot;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            slot = itemView.findViewById(R.id.slot);
        }
    }
}
