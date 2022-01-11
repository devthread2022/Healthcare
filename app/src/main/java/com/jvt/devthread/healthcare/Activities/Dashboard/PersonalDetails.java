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
import com.jvt.devthread.healthcare.databinding.FragmentPersonalDetailsBinding;

public class PersonalDetails extends Fragment {
    private FragmentPersonalDetailsBinding binding;
    String userName, profile, contact, email, gender, dob, bloodGroup, maritalStatus, height, weight, emergencyContact, location;
    DatabaseReference databaseReference;
    String uid;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPersonalDetailsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        databaseReference.child("Users").child(uid).child("PersonalDetail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    userName = snapshot.child("userName").getValue().toString();
                    contact = snapshot.child("contact").getValue().toString();
                    email = snapshot.child("email").getValue().toString();
                    gender = snapshot.child("gender").getValue().toString();
                    dob = snapshot.child("dob").getValue().toString();
                    bloodGroup = snapshot.child("bloodGroup").getValue().toString();
                    maritalStatus = snapshot.child("maritalStatus").getValue().toString();
                    height = snapshot.child("height").getValue().toString();
                    weight = snapshot.child("weight").getValue().toString();
                    emergencyContact = snapshot.child("emergencyContact").getValue().toString();
                    binding.name.setText(userName);
                    binding.contact.setText(contact);
                    binding.email.setText(email);
                    binding.gender.setText(gender);
                    binding.dob.setText(dob);
                    binding.blood.setText(bloodGroup);
                    binding.marital.setText(maritalStatus);
                    binding.height.setText(height);
                    binding.weight.setText(weight);
                    binding.emergency.setText(emergencyContact);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}