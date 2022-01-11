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
import com.jvt.devthread.healthcare.Activities.Model.PersonalLifestyleModel;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentEditLifestyleBinding;

public class EditLifestyle extends Fragment {
    private FragmentEditLifestyleBinding binding;
    String smokingHabit, alcoholConsumption, foodPreference, occupation;
    DatabaseReference databaseReference,databaseReference2;
    String uid;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditLifestyleBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        databaseReference.child("Users").child(uid).child("PersonalLifestyle").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    smokingHabit = snapshot.child("smokingHabit").getValue().toString();
                    alcoholConsumption = snapshot.child("alcoholConsumption").getValue().toString();
                    foodPreference = snapshot.child("foodPreference").getValue().toString();
                    occupation = snapshot.child("occupation").getValue().toString();
                    binding.smoking.setText(smokingHabit);
                    binding.alcohol.setText(alcoholConsumption);
                    binding.food.setText(foodPreference);
                    binding.occupation.setText(occupation);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.submit.setOnClickListener(view1 -> {
            smokingHabit = binding.smoking.getText().toString();
            alcoholConsumption = binding.alcohol.getText().toString();
            foodPreference = binding.food.getText().toString();
            occupation = binding.occupation.getText().toString();
            PersonalLifestyleModel personalLifestyleModel = new PersonalLifestyleModel(smokingHabit, alcoholConsumption,
                    foodPreference, occupation);
            databaseReference2.child("Users").child(uid).child("PersonalLifestyle").setValue(personalLifestyleModel);
            Toast.makeText(getContext(), "Updated!", Toast.LENGTH_SHORT).show();

        });
        return view;
    }
}