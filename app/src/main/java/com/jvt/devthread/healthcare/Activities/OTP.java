package com.jvt.devthread.healthcare.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.healthcare.Activities.Common.Common;
import com.jvt.devthread.healthcare.Activities.Model.PersonalDetailModel;
import com.jvt.devthread.healthcare.Activities.Model.PersonalLifestyleModel;
import com.jvt.devthread.healthcare.Activities.Model.PersonalMedicalDetailModel;
import com.jvt.devthread.healthcare.Activities.Model.WalletModel;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.ActivityOTPBinding;

import java.util.concurrent.TimeUnit;

public class OTP extends AppCompatActivity {
    private ActivityOTPBinding binding;
    private FirebaseAuth mAuth;
    private String verificationId;
    Long walletBalance;
    DatabaseReference databaseReference,databaseReferenceUsers,databaseReferenceMedical,databaseReferenceWallet;
    String userName="", profile="", contact, email="", gender="", dob="", bloodGroup="", maritalStatus="", height="", weight="", emergencyContact="", location="";
    String allergies="", currentMedication="", pastMedication="", chronicDisease="", injuries="", surgeries="";
    String smokingHabit="", alcoholConsumption="", foodPreference="", occupation="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);
        binding = ActivityOTPBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();
        sendVerificationCode(Common.phone);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReferenceUsers = FirebaseDatabase.getInstance().getReference();
        databaseReferenceMedical = FirebaseDatabase.getInstance().getReference();
        databaseReferenceWallet = FirebaseDatabase.getInstance().getReference();
        binding.numberText.setText("One Time Password sent to\n"+ Common.phone);
        binding.submit.setOnClickListener(view1 -> {
            String pin = binding.pinView.getText().toString();
            if (pin.isEmpty()){
                Toast.makeText(this, "Please enter otp..", Toast.LENGTH_SHORT).show();
            }else {
                verifyCode(pin);
            }
        });

    }
    private void sendVerificationCode(String phone) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                binding.pinView.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OTP.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }
    };
    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        signInWithCredential(credential);
    }
    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            String UID = user.getUid();
                            DatabaseReference databaseReferenceCheck = FirebaseDatabase.getInstance().getReference();
                            databaseReferenceCheck.child("Users").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.hasChild(UID)){
                                        Toast.makeText(OTP.this, "Logged in.", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(OTP.this, MainActivity.class);
                                        startActivity(i);
                                    }else {
                                        contact = Common.phone;
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
                                        Toast.makeText(OTP.this, "Logged in.", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(OTP.this, MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            Toast.makeText(OTP.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}