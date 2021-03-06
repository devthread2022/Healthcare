package com.jvt.devthread.healthcare.Activities.Dashboard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.healthcare.Activities.Adapters.SpotLightAdapter;
import com.jvt.devthread.healthcare.Activities.Doctors.Doctors;
import com.jvt.devthread.healthcare.Activities.Labtest.LabTest;
import com.jvt.devthread.healthcare.Activities.Model.SpotlightModel;
import com.jvt.devthread.healthcare.Activities.OnlineAppointment.OnlineAppointment;
import com.jvt.devthread.healthcare.Activities.Surgery.Surgery;
import com.jvt.devthread.healthcare.R;
import com.jvt.devthread.healthcare.databinding.FragmentDashboardBinding;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dashboard extends Fragment {
    private FragmentDashboardBinding binding;
    public static Fragment activeFragment;
    Dialog dialog;
    ImageView close;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    DatabaseReference databaseReferenceWallet,databaseReference,databaseReference1;
    FirebaseAuth firebaseAuth;
    String uid,name,email,mobile;
    Long walletBalance;
    TextView user, regNumber,profile,gallery;
    CardView logout;
    private List<SpotlightModel> spotlightModelList = new ArrayList<>();
    FusedLocationProviderClient fusedLocationProviderClient;
    String userLocation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.menu_options_layout);
        close = dialog.findViewById(R.id.cancel);
        user = dialog.findViewById(R.id.user);
        regNumber = dialog.findViewById(R.id.regnumber);
        profile = dialog.findViewById(R.id.profile);
        gallery = dialog.findViewById(R.id.gallery);
        logout = dialog.findViewById(R.id.logout);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReferenceWallet = FirebaseDatabase.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        uid = firebaseAuth.getCurrentUser().getUid();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            getUserLocation();
        }else {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
        databaseReferenceWallet.child("Users").child(uid).child("WalletBalance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    walletBalance = Long.valueOf(snapshot.child("walletBalance").getValue().toString());
                    binding.walletBal.setText(convertValueInIndianCurrency(walletBalance));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("Users").child(uid).child("PersonalDetail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    name = snapshot.child("userName").getValue().toString();
                    email = snapshot.child("email").getValue().toString();
                    mobile = snapshot.child("contact").getValue().toString();
                    user.setText(name);
                    if (email.equals("")){
                        regNumber.setText(mobile);
                    }else if (mobile.equals("")){
                        regNumber.setText(email);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        binding.menu.setOnClickListener(view1 -> {
            dialog.show();
        });
        close.setOnClickListener(view1 -> {
            dialog.dismiss();
        });
        profile.setOnClickListener(view1 -> {
            dialog.dismiss();
            Fragment fragment = new Profile();
            loadFragment(fragment,"Profile");
        });
        gallery.setOnClickListener(view1 -> {
            dialog.dismiss();
            Fragment fragment = new Gallery();
            loadFragment(fragment,"Gallery");
        });
        binding.bookAppoint.setOnClickListener(view1 -> {
            Fragment appointment = new OnlineAppointment();
            loadFragment(appointment,"OnlineAppointment");
        });
        binding.doctors.setOnClickListener(view1 -> {
            Fragment doctors = new Doctors();
            loadFragment(doctors,"Doctors");
        });
        binding.lab.setOnClickListener(view1 -> {
            Fragment lab = new LabTest();
            loadFragment(lab,"LabTest");
        });
        binding.surgery.setOnClickListener(view1 -> {
            Fragment surgery = new Surgery();
            loadFragment(surgery,"Surgery");
        });
        binding.spotRecycler.setHasFixedSize(true);
        binding.spotRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        databaseReference1.child("InSpotlight").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    spotlightModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        SpotlightModel spotlightModel = dataSnapshot.getValue(SpotlightModel.class);
                        spotlightModelList.add(spotlightModel);
                    }
                    SpotLightAdapter spotLightAdapter = new SpotLightAdapter(getContext(),spotlightModelList);
                    binding.spotRecycler.setAdapter(spotLightAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
    public String convertValueInIndianCurrency(Long amount)
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("hi", "IN"));
        return formatter.format(amount);
    }
    private void loadFragment(Fragment fragment, String tag) {
        executorService.execute(() -> {
            if (fragment != null) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment, tag).addToBackStack(tag).commit();

            }
            handler.post(() -> {
                activeFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            });
        });
    }
    @SuppressLint("MissingPermission")
    private void getUserLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null){
                    try {
                        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(),location.getLongitude(),1
                        );
                        userLocation = addresses.get(0).getLocality()+","+addresses.get(0).getCountryName();
                        binding.location.setText(userLocation);
                    }catch (Exception e){
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}