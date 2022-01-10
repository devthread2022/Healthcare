package com.jvt.devthread.healthcare.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.ActivityUseOptionsBinding;

public class UseOptions extends AppCompatActivity {
    private ActivityUseOptionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_options);
        binding = ActivityUseOptionsBinding.inflate(getLayoutInflater());
        View view =binding.getRoot();
        setContentView(view);
        binding.login.setOnClickListener(view1 -> {
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
        });
        binding.signup.setOnClickListener(view1 -> {
            Intent intent = new Intent(this,Signup.class);
            startActivity(intent);
        });
    }
}