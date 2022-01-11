package com.jvt.devthread.healthcare.Activities.Dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvt.devthread.healthcare.Activities.Adapters.ContestsPagerAdapter;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentProfileBinding;

public class Profile extends Fragment {
    private FragmentProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        initViews();
        return view;
    }
    private void initViews() {
        setupViewPager(binding.pager);
        binding.tabLayout.setupWithViewPager(binding.pager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ContestsPagerAdapter contestsPagerAdapter = new ContestsPagerAdapter(getChildFragmentManager());
        contestsPagerAdapter.addFragment(new PersonalDetails(),"Personal");
        contestsPagerAdapter.addFragment(new MedicalDetails(),"Medical");
        contestsPagerAdapter.addFragment(new Lifestyle(),"Lifestyle");
        viewPager.setAdapter(contestsPagerAdapter);
    }
}