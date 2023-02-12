package com.example.ahendrivingschool;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DescribeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    String s_address,  s_mail,  s_mobile,  s_name,  s_pincode,  s_profile;



    public DescribeFragment() {
    }

    public DescribeFragment(String s_address, String s_mail, String s_mobile, String s_name, String s_pincode, String s_profile) {
        this.s_address=s_address;
        this.s_mail=s_mail;
        this.s_mobile=s_mobile;
        this.s_name=s_name;
        this.s_pincode=s_pincode;
        this.s_profile=s_profile;

    }


    public static DescribeFragment newInstance(String param1, String param2) {
        DescribeFragment fragment = new DescribeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_describe, container, false);

        ImageView sclprofile = view.findViewById(R.id.sclprofile);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView scl_name = view.findViewById(R.id.sclname);
        TextView scl_address = view.findViewById(R.id.scl_address);
        TextView scl_pincode = view.findViewById(R.id.scl_pincode);
        TextView scl_mail = view.findViewById(R.id.scl_mail);
        TextView scl_phoneNo = view.findViewById(R.id.PhoneNo);
        //TextView sclname = view.findViewById(R.id.sclname);

        //intent to previous btn
        //previous btn to home page
        Button btn_previous = view.findViewById(R.id.previous);
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), HomePage.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0,0);
            }
        });


        scl_name.setText(s_name);
        scl_address.setText(s_address);
        scl_mail.setText(s_mail);
        scl_pincode.setText(s_pincode);
        scl_phoneNo.setText(s_mobile);



        return view;


    }

    public void onBackPressed(){
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new recfragment()).addToBackStack(null).commit();
    }
}