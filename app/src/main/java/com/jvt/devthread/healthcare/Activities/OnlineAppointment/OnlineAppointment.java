package com.jvt.devthread.healthcare.Activities.OnlineAppointment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.healthcare.Activities.Adapters.ConcernIssuesAdapter;
import com.jvt.devthread.healthcare.Activities.Doctors.Doctors;
import com.jvt.devthread.healthcare.Activities.Model.ConcernIssuesModel;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentOnlineAppointmentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OnlineAppointment extends Fragment {
    private FragmentOnlineAppointmentBinding binding;
    DatabaseReference databaseReference;
    private List<ConcernIssuesModel> concernIssuesModelList = new ArrayList<>();
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    public static Fragment activeFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentOnlineAppointmentBinding.inflate(inflater,container,false);
       View view = binding.getRoot();
       binding.concernRecycler.setHasFixedSize(true);
       binding.concernRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
       databaseReference = FirebaseDatabase.getInstance().getReference();
       databaseReference.child("ConcernIssues").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if (snapshot.exists()){
                   concernIssuesModelList.clear();
                   for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                       ConcernIssuesModel concernIssuesModel = dataSnapshot.getValue(ConcernIssuesModel.class);
                       concernIssuesModelList.add(concernIssuesModel);
                   }
                   ConcernIssuesAdapter concernIssuesAdapter = new ConcernIssuesAdapter(getContext(),concernIssuesModelList);
                   binding.concernRecycler.setAdapter(concernIssuesAdapter);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
       binding.womenHealth.setOnClickListener(view1 -> {
           Fragment fragment = new Doctors();
           loadFragment(fragment,"Doctors");
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