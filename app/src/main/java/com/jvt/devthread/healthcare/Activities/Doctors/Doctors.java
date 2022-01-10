package com.jvt.devthread.healthcare.Activities.Doctors;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.healthcare.Activities.Adapters.DoctorsAdapter;
import com.jvt.devthread.healthcare.Activities.Model.DoctorModel;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentDoctorsBinding;

import java.util.ArrayList;
import java.util.List;

public class Doctors extends Fragment {
    private FragmentDoctorsBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<DoctorModel> doctorModelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDoctorsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        binding.docRecycler.setHasFixedSize(true);
        binding.docRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child("Doctors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    doctorModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        DoctorModel doctorModel = dataSnapshot.getValue(DoctorModel.class);
                        doctorModelList.add(doctorModel);
                    }
                    DoctorsAdapter doctorsAdapter = new DoctorsAdapter(getContext(),doctorModelList);
                    binding.docRecycler.setAdapter(doctorsAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}