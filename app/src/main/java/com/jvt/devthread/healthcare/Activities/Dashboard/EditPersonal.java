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
import com.jvt.devthread.healthcare.Activities.Model.PersonalDetailModel;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentEditPersonalBinding;

public class EditPersonal extends Fragment {
    private FragmentEditPersonalBinding binding;
    String userName, profile, contact, email, gender, dob, bloodGroup, maritalStatus, height, weight, emergencyContact, location;
    DatabaseReference databaseReference,databaseReference2;
    String uid;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditPersonalBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference();
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
                    profile = snapshot.child("profile").getValue().toString();
                    location = snapshot.child("location").getValue().toString();
                    binding.name.setText(userName);
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
        binding.submit.setOnClickListener(view1 -> {
            userName = binding.name.getText().toString();
            gender = binding.gender.getText().toString();
            dob = binding.dob.getText().toString();
            bloodGroup = binding.blood.getText().toString();
            maritalStatus = binding.marital.getText().toString();
            height = binding.height.getText().toString();
            weight = binding.weight.getText().toString();
            emergencyContact = binding.emergency.getText().toString();
            PersonalDetailModel personalDetailModel = new PersonalDetailModel(userName, profile, contact, email, gender, dob, bloodGroup,
                    maritalStatus, height, weight, emergencyContact, location);
            databaseReference2.child("Users").child(uid).child("PersonalDetail").setValue(personalDetailModel);
            Toast.makeText(getContext(), "Updated!", Toast.LENGTH_SHORT).show();
        });
        return view;
    }
}