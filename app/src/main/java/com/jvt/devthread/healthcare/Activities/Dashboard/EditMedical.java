package com.jvt.devthread.healthcare.Activities.Dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentEditMedicalBinding;

public class EditMedical extends Fragment {
    private FragmentEditMedicalBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditMedicalBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
}