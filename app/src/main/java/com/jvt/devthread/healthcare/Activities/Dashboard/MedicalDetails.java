package com.jvt.devthread.healthcare.Activities.Dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentMedicalDetailsBinding;

public class MedicalDetails extends Fragment {
    private FragmentMedicalDetailsBinding binding;
    String allergies, currentMedication, pastMedication, chronicDisease, injuries, surgeries;
    DatabaseReference databaseReference;
    String uid;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMedicalDetailsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        databaseReference.child("Users").child(uid).child("PersonalMedicalDetail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    allergies = snapshot.child("allergies").getValue().toString();
                    currentMedication = snapshot.child("currentMedication").getValue().toString();
                    pastMedication = snapshot.child("pastMedication").getValue().toString();
                    chronicDisease = snapshot.child("chronicDisease").getValue().toString();
                    injuries = snapshot.child("injuries").getValue().toString();
                    surgeries = snapshot.child("surgeries").getValue().toString();
                    binding.allergy.setText(allergies);
                    binding.medication.setText(currentMedication);
                    binding.pastMedi.setText(pastMedication);
                    binding.disease.setText(chronicDisease);
                    binding.injury.setText(injuries);
                    binding.surgery.setText(surgeries);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}