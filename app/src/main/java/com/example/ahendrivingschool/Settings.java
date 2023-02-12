package com.example.ahendrivingschool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.snapshot.Index;

public class Settings extends Fragment {
    Activity context;
    TextView LogOut;
    TextView profile,contact,licenceprocess,shareapp,rateus;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        context=getActivity();
        View view= inflater.inflate(R.layout.fragment_settings, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        profile=context.findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,UserProfile.class);
                startActivity(intent);
            }
        });
        contact=context.findViewById(R.id.contact);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Contact.class);
                startActivity(intent);
            }
        });

        licenceprocess=context.findViewById(R.id.licenceprocess);
        licenceprocess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,LicenceProcess.class);
                startActivity(intent);
            }
        });

        shareapp=context.findViewById(R.id.shareapp);
        shareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ShareApp.class);
                startActivity(intent);
            }
        });

        rateus=context.findViewById(R.id.rateus);
        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,RateUs.class);
                startActivity(intent);
            }
        });


        licenceprocess=context.findViewById(R.id.licenceprocess);
        licenceprocess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,LicenceProcess.class);
                startActivity(intent);
            }
        });

        LogOut=context.findViewById(R.id.logout);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(context,UserLogin.class));
                context.finish();
            }
        });
    }
}