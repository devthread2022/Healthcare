package com.jvt.devthread.healthcare.Activities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jvt.devthread.healthcare.Activities.Common.Common;
import com.jvt.devthread.healthcare.Activities.Doctors.DoctorsTiming;
import com.jvt.devthread.healthcare.Activities.Doctors.Doctors_details;
import com.jvt.devthread.healthcare.Activities.Model.DoctorModel;
import com.jvt.devthread.healthcare.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.ViewHolder> {
    public Context context;
    private List<DoctorModel> doctorModelList;

    public DoctorsAdapter(Context context, List<DoctorModel> doctorModelList) {
        this.context = context;
        this.doctorModelList = doctorModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoctorModel doctorModel = doctorModelList.get(position);
        Glide.with(context).load(doctorModel.getDocImage()).into(holder.docPic);
        holder.name.setText(doctorModel.getDocName());
        holder.specialization.setText(doctorModel.getDocSpecialization());
        holder.experience.setText(doctorModel.getDocExperience());
        holder.address.setText(doctorModel.getDocAddress());
        holder.fee.setText(String.valueOf(doctorModel.getConsultationFee()));
        holder.itemView.setOnClickListener(view -> {
            Common.docId = doctorModelList.get(position).getDocId();
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment fragment = new Doctors_details();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
                    .addToBackStack(null).commit();
        });
        holder.book.setOnClickListener(view -> {
            Common.docId = doctorModelList.get(position).getDocId();
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment fragment = new DoctorsTiming();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
                    .addToBackStack(null).commit();
        });
    }

    @Override
    public int getItemCount() {
        return doctorModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, specialization, experience, likes, stories,address, fee;
        LinearLayout book;
        CircleImageView docPic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            specialization = itemView.findViewById(R.id.specialization);
            experience = itemView.findViewById(R.id.experience);
            likes = itemView.findViewById(R.id.likes);
            stories = itemView.findViewById(R.id.patient_stories);
            address = itemView.findViewById(R.id.address);
            fee = itemView.findViewById(R.id.consultation_fee);
            book = itemView.findViewById(R.id.bookAppoint);
            docPic = itemView.findViewById(R.id.image);
        }
    }
}
