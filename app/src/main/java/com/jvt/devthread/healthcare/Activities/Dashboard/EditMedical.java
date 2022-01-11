package com.jvt.devthread.healthcare.Activities.Dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.healthcare.Activities.Model.PersonalMedicalDetailModel;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentEditMedicalBinding;

public class EditMedical extends Fragment {
    private FragmentEditMedicalBinding binding;
    String allergies, currentMedication, pastMedication, chronicDisease, injuries, surgeries;
    DatabaseReference databaseReference,databaseReference2;
    String uid;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditMedicalBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        databaseReference2 = FirebaseDatabase.getInstance().getReference();
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
        binding.submit.setOnClickListener(view1 -> {
            allergies = binding.allergy.getText().toString();
            currentMedication = binding.medication.getText().toString();
            pastMedication = binding.pastMedi.getText().toString();
            chronicDisease = binding.disease.getText().toString();
            injuries = binding.injury.getText().toString();
            surgeries = binding.surgery.getText().toString();
            PersonalMedicalDetailModel personalMedicalDetailModel = new PersonalMedicalDetailModel(allergies, currentMedication, pastMedication,
                    chronicDisease, injuries, surgeries);
            databaseReference2.child("Users").child(uid).child("PersonalMedicalDetail").setValue(personalMedicalDetailModel);
            Toast.makeText(getContext(), "Updated!", Toast.LENGTH_SHORT).show();

        });
        return view;
    }
}