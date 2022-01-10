package com.jvt.devthread.healthcare.Activities.Surgery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentSurgeryBinding;

public class Surgery extends Fragment {
    private FragmentSurgeryBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSurgeryBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
}