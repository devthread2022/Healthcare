package com.jvt.devthread.healthcare.Activities.Doctors;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.healthcare.Activities.Common.Common;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentConfirmAppointmentBinding;

public class ConfirmAppointment extends Fragment {
    private FragmentConfirmAppointmentBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    String docId,docName, docSpecialization, docExperience, docEducation, docAddress,membership, registration, about,docImage;
    Long consultationFee;
    String radio = "false";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConfirmAppointmentBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        binding.time.setText(Common.sDate+" "+Common.sTime);
        databaseReference.child("Doctors").child(Common.docId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    docName = snapshot.child("docName").getValue().toString();
                    docSpecialization = snapshot.child("docSpecialization").getValue().toString();
                    docExperience = snapshot.child("docExperience").getValue().toString();
                    docEducation = snapshot.child("docEducation").getValue().toString();
                    docAddress = snapshot.child("docAddress").getValue().toString();
                    membership = snapshot.child("membership").getValue().toString();
                    registration = snapshot.child("registration").getValue().toString();
                    about = snapshot.child("about").getValue().toString();
                    docImage = snapshot.child("docImage").getValue().toString();
                    consultationFee = Long.valueOf(snapshot.child("consultationFee").getValue().toString());
                    binding.name.setText(docName);
                    binding.specialization.setText(docSpecialization);
                    binding.experience.setText(docExperience);
                    Glide.with(getContext()).load(docImage).into(binding.image);
                    binding.fee.setText(String.valueOf(consultationFee));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}