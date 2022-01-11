package com.jvt.devthread.healthcare.Activities.Dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentEditLifestyleBinding;

public class EditLifestyle extends Fragment {
    private FragmentEditLifestyleBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditLifestyleBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
}