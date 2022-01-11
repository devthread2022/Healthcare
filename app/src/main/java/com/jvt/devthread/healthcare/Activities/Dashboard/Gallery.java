package com.jvt.devthread.healthcare.Activities.Dashboard;

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
import com.jvt.devthread.healthcare.Activities.Adapters.GalleryAdapter;
import com.jvt.devthread.healthcare.Activities.Model.GalleryModel;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentGalleryBinding;

import java.util.ArrayList;
import java.util.List;

public class Gallery extends Fragment {
    private FragmentGalleryBinding binding;
    DatabaseReference databaseReference;
    private List<GalleryModel> galleryModelList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        binding.galleryRecycler.setHasFixedSize(true);
        binding.galleryRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child("Gallery").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    galleryModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        GalleryModel galleryModel = dataSnapshot.getValue(GalleryModel.class);
                        galleryModelList.add(galleryModel);
                    }
                    GalleryAdapter galleryAdapter = new GalleryAdapter(getContext(),galleryModelList);
                    binding.galleryRecycler.setAdapter(galleryAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}