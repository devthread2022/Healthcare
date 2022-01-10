package com.jvt.devthread.healthcare.Activities.Labtest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentLabTestBinding;

public class LabTest extends Fragment {
    private FragmentLabTestBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLabTestBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
}