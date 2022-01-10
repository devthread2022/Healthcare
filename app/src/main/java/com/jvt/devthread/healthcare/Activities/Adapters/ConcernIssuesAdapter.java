package com.jvt.devthread.healthcare.Activities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jvt.devthread.healthcare.Activities.Common.Common;
import com.jvt.devthread.healthcare.Activities.Doctors.Doctors;
import com.jvt.devthread.healthcare.Activities.Doctors.Doctors_details;
import com.jvt.devthread.healthcare.Activities.Model.ConcernIssuesModel;
import com.jvt.devthread.healthcare.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConcernIssuesAdapter extends RecyclerView.Adapter<ConcernIssuesAdapter.ViewHolder> {
    public Context context;
    private List<ConcernIssuesModel> concernIssuesModelList;

    public ConcernIssuesAdapter(Context context, List<ConcernIssuesModel> concernIssuesModelList) {
        this.context = context;
        this.concernIssuesModelList = concernIssuesModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.concern_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConcernIssuesModel concernIssuesModel = concernIssuesModelList.get(position);
        Glide.with(context).load(concernIssuesModel.getIcon()).into(holder.image);
        holder.problem.setText(concernIssuesModel.getConcernName());
        holder.itemView.setOnClickListener(view -> {
            Common.problem = concernIssuesModelList.get(position).getConcernName();
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment fragment = new Doctors();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
                    .addToBackStack(null).commit();
        });
    }

    @Override
    public int getItemCount() {
        return concernIssuesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView problem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image =itemView.findViewById(R.id.image);
            problem = itemView.findViewById(R.id.problem_name);
        }
    }
}
