package com.jvt.devthread.healthcare.Activities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jvt.devthread.healthcare.Activities.Model.SpotlightModel;
import com.jvt.devthread.healthcare.R;

import java.util.List;

public class SpotLightAdapter extends RecyclerView.Adapter<SpotLightAdapter.ViewHolder> {
    public Context context;
    private List<SpotlightModel> spotlightModelList;

    public SpotLightAdapter(Context context, List<SpotlightModel> spotlightModelList) {
        this.context = context;
        this.spotlightModelList = spotlightModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.insights_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpotlightModel spotlightModel = spotlightModelList.get(position);
        Glide.with(context).load(spotlightModel.getPic()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return spotlightModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
