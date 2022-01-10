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

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.healthcare.Activities.Adapters.PatientStoriesAdapter;
import com.jvt.devthread.healthcare.Activities.Adapters.RecommendedAdapter;
import com.jvt.devthread.healthcare.Activities.Adapters.ServicesAdapter;
import com.jvt.devthread.healthcare.Activities.Common.Common;
import com.jvt.devthread.healthcare.Activities.Model.PatientStoriesModel;
import com.jvt.devthread.healthcare.Activities.Model.RecommendedModel;
import com.jvt.devthread.healthcare.Activities.Model.ServiceModel;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentDoctorsDetailsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Doctors_details extends Fragment {
    private FragmentDoctorsDetailsBinding binding;
    DatabaseReference databaseReference1,databaseReference2,databaseReference3,databaseReference4;
    String docId,docName, docSpecialization, docExperience, docEducation, docAddress,membership, registration, about,docImage;
    Long consultationFee;
    private List<RecommendedModel> recommendedModelList = new ArrayList<>();
    private List<PatientStoriesModel> patientStoriesModelList = new ArrayList<>();
    private List<ServiceModel> serviceModelList = new ArrayList<>();
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    public static Fragment activeFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDoctorsDetailsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference();
        databaseReference3 = FirebaseDatabase.getInstance().getReference();
        databaseReference4 = FirebaseDatabase.getInstance().getReference();
        binding.recommendedRecycler.setHasFixedSize(true);
        binding.recommendedRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.servicesRecycler.setHasFixedSize(true);
        binding.servicesRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.storiesRecycler.setHasFixedSize(true);
        binding.storiesRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference1.child("Doctors").child(Common.docId).addValueEventListener(new ValueEventListener() {
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
                    binding.hospital.setText(docAddress);
                    binding.fee.setText("Rs."+consultationFee);
                    binding.specialization1.setText(docSpecialization);
                    binding.about.setText(about);
                    binding.education.setText(docEducation);
                    binding.membership.setText(membership);
                    binding.registration.setText(registration);
                    binding.experience1.setText(docExperience);
                    Glide.with(getContext()).load(docImage).into(binding.image);
                    binding.fee.setText(String.valueOf(consultationFee));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference2.child("Doctors").child(Common.docId).child("RecommendedFor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    recommendedModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        RecommendedModel recommendedModel = dataSnapshot.getValue(RecommendedModel.class);
                        recommendedModelList.add(recommendedModel);
                    }
                    RecommendedAdapter recommendedAdapter = new RecommendedAdapter(getContext(),recommendedModelList);
                    binding.recommendedRecycler.setAdapter(recommendedAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference3.child("Doctors").child(Common.docId).child("PatientStories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    patientStoriesModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        PatientStoriesModel patientStoriesModel = dataSnapshot.getValue(PatientStoriesModel.class);
                        patientStoriesModelList.add(patientStoriesModel);
                    }
                    PatientStoriesAdapter patientStoriesAdapter = new PatientStoriesAdapter(getContext(),patientStoriesModelList);
                    binding.storiesRecycler.setAdapter(patientStoriesAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference4.child("Doctors").child(Common.docId).child("Services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    serviceModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ServiceModel serviceModel = dataSnapshot.getValue(ServiceModel.class);
                        serviceModelList.add(serviceModel);
                    }
                    ServicesAdapter servicesAdapter = new ServicesAdapter(getContext(),serviceModelList);
                    binding.servicesRecycler.setAdapter(servicesAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.bookAppoint.setOnClickListener(view1 -> {
            Fragment fragment = new DoctorsTiming();
            loadFragment(fragment,"DoctorsTiming");
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