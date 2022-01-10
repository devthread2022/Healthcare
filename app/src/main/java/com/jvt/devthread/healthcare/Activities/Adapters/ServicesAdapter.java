package com.jvt.devthread.healthcare.Activities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.healthcare.Activities.Model.ServiceModel;
import com.jvt.devthread.healthcare.R;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {
    public Context context;
    private List<ServiceModel> serviceModelList;

    public ServicesAdapter(Context context, List<ServiceModel> serviceModelList) {
        this.context = context;
        this.serviceModelList = serviceModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceModel serviceModel = serviceModelList.get(position);
        holder.service.setText(serviceModel.getServiceName());
    }

    @Override
    public int getItemCount() {
        return serviceModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView service;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            service = itemView.findViewById(R.id.services);
        }
    }
}
