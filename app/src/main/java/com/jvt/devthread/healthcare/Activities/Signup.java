package com.jvt.devthread.healthcare.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jvt.devthread.healthcare.Activities.Model.PersonalDetailModel;
import com.jvt.devthread.healthcare.Activities.Model.PersonalLifestyleModel;
import com.jvt.devthread.healthcare.Activities.Model.PersonalMedicalDetailModel;
import com.jvt.devthread.healthcare.Activities.Model.WalletModel;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.ActivitySignupBinding;

import java.util.Objects;

public class Signup extends AppCompatActivity {
    private ActivitySignupBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReferenceUsers,databaseReferenceCheck,databaseReferenceWallet;
    Long walletBalance;
    String email, password, confirmPassword;
    String userName="", profile="", contact="", gender="", dob="", bloodGroup="", maritalStatus="", height="", weight="", emergencyContact="", location="";
    String allergies="", currentMedication="", pastMedication="", chronicDisease="", injuries="", surgeries="";
    String smokingHabit="", alcoholConsumption="", foodPreference="", occupation="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        databaseReferenceUsers = FirebaseDatabase.getInstance().getReference();
        databaseReferenceCheck = FirebaseDatabase.getInstance().getReference();
        databaseReferenceWallet = FirebaseDatabase.getInstance().getReference();
        binding.submit.setOnClickListener(view1 -> {
            email = binding.email.getText().toString();
            password = binding.password.getText().toString();
            confirmPassword = binding.confirmPassword.getText().toString();
            if (email.isEmpty()){
                binding.email.requestFocus();
                binding.email.setError("Email");
            }else if (password.isEmpty()){
                binding.password.requestFocus();
                binding.password.setError("Password");
            }else if (confirmPassword.isEmpty()){
                binding.confirmPassword.requestFocus();
                binding.confirmPassword.setError("Confirm password");
            }else if (!password.equals(confirmPassword)){
                Toast.makeText(this, "Password does not match please check!", Toast.LENGTH_SHORT).show();
            }else {
                createAccount(email,password);
            }
        });
    }

    private void createAccount(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String UID= Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                PersonalDetailModel personalDetailModel = new PersonalDetailModel(userName, profile, contact, email, gender, dob, bloodGroup,
                        maritalStatus, height, weight, emergencyContact, location);
                PersonalLifestyleModel personalLifestyleModel = new PersonalLifestyleModel(smokingHabit, alcoholConsumption,
                        foodPreference, occupation);
                PersonalMedicalDetailModel personalMedicalDetailModel = new PersonalMedicalDetailModel(allergies, currentMedication, pastMedication, chronicDisease,
                        injuries, surgeries);
                walletBalance = Long.valueOf(0);
                WalletModel walletModel = new WalletModel(walletBalance);
                databaseReferenceWallet.child("Users").child(UID).child("WalletBalance").setValue(walletModel);
                databaseReference.child("Users").child(UID).child("PersonalDetail").setValue(personalDetailModel);
                databaseReferenceUsers.child("Users").child(UID).child("PersonalLifestyle").setValue(personalLifestyleModel);
                databaseReferenceCheck.child("Users").child(UID).child("PersonalMedicalDetail").setValue(personalMedicalDetailModel);
                Toast.makeText(this, "Account created successfully. Please login!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,Login.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}