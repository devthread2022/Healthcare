package com.jvt.devthread.healthcare.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jvt.devthread.healthcare.Activities.Common.Common;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.ActivityOnboardingBinding;

public class Onboarding extends AppCompatActivity {
    private ActivityOnboardingBinding binding;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(binding.mobile.getText().toString())) {
                    Toast.makeText(Onboarding.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    String code = binding.code.getText().toString();
                    String phone = code + binding.mobile.getText().toString();
                    Common.phone = phone;
                    Intent intent = new Intent(Onboarding.this,OTP.class);
                    startActivity(intent);
                }
            }
        });
        binding.otherOption.setOnClickListener(view1 -> {
            Intent i = new Intent(this,UseOptions.class);
            startActivity(i);
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}