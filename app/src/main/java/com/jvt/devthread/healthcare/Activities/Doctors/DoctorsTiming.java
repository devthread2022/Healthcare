package com.jvt.devthread.healthcare.Activities.Doctors;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.healthcare.Activities.Adapters.AvailableDatesAdapter;
import com.jvt.devthread.healthcare.Activities.Adapters.AvailableTimeAdapter;
import com.jvt.devthread.healthcare.Activities.Common.Common;
import com.jvt.devthread.healthcare.Activities.Model.DateModel;
import com.jvt.devthread.healthcare.Activities.Model.TimeModel;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentDoctorsTimingBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoctorsTiming extends Fragment {
    private FragmentDoctorsTimingBinding binding;
    DatabaseReference databaseReference1, databaseReference2;
    private List<DateModel> dateModelList = new ArrayList<>();
    private List<TimeModel> timeModelList = new ArrayList<>();
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    public static Fragment activeFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDoctorsTimingBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference();
        binding.dateRecycler.setHasFixedSize(true);
        binding.dateRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.timeRecycler.setHasFixedSize(true);
        binding.timeRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        databaseReference1.child("Doctors").child(Common.docId).child("AvailableDates").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    dateModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        DateModel dateModel = dataSnapshot.getValue(DateModel.class);
                        dateModelList.add(dateModel);
                    }
                    AvailableDatesAdapter availableDatesAdapter = new AvailableDatesAdapter(getContext(),dateModelList);
                    binding.dateRecycler.setAdapter(availableDatesAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference2.child("Doctors").child(Common.docId).child("AvailableTime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    timeModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        TimeModel dateModel = dataSnapshot.getValue(TimeModel.class);
                        timeModelList.add(dateModel);
                    }
                    AvailableTimeAdapter availableDatesAdapter = new AvailableTimeAdapter(getContext(),timeModelList);
                    binding.timeRecycler.setAdapter(availableDatesAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.submit.setOnClickListener(view1 -> {
            Fragment confirm = new ConfirmAppointment();
            loadFragment(confirm,"ConfirmAppointment");
        });
        return view;
    }
    private void loadFragment(Fragment fragment, String tag) {
        executorService.execute(() -> {
            if (fragment != null) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment, tag).addToBackStack(tag).commit();

            }
            handler.post(() -> {
                activeFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            });
        });
    }
}