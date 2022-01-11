package com.jvt.devthread.healthcare.Activities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jvt.devthread.healthcare.Activities.Model.GalleryModel;
import com.jvt.devthread.healthcare.R;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    public Context context;
    private List<GalleryModel> galleryModelList;

    public GalleryAdapter(Context context, List<GalleryModel> galleryModelList) {
        this.context = context;
        this.galleryModelList = galleryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GalleryModel galleryModel = galleryModelList.get(position);
        holder.description.setText(galleryModel.getDescription());
        Glide.with(context).load(galleryModel.getPic()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return galleryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.desc);
        }
    }
}
