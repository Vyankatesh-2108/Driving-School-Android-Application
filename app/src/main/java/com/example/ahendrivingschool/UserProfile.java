package com.example.ahendrivingschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {

    TextView u_mail, u_name, u_mobile, u_address, u_aadhar, u_pancard, u_birth;

    FirebaseAuth mAuth;

    FirebaseUser user;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        u_mail = findViewById(R.id.u_mail);
        u_name = findViewById(R.id.u_name);
        u_mobile = findViewById(R.id.u_mobile);
        u_address = findViewById(R.id.u_address);
        u_aadhar = findViewById(R.id.u_adhar);
        u_pancard = findViewById(R.id.u_pancard);
        u_birth = findViewById(R.id.u_birth);



        String uid = UserLogin.uid;

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");

        reference.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();


                        //  String mail = dataSnapshot.child("u_mail").getValue(String.class);
                        //  String name = dataSnapshot.child("u_name").getValue(String.class);
                        //  String mobile = dataSnapshot.child("u_mobile").getValue(String.class);


                        String name = String.valueOf(dataSnapshot.child("u_name").getValue(String.class));
                        String email = String.valueOf(dataSnapshot.child("u_mail").getValue(String.class));
                        String mobile = String.valueOf(dataSnapshot.child("u_mobile").getValue(String.class));
                        String address = String.valueOf(dataSnapshot.child("u_address").getValue(String.class));
                        String aadhar = String.valueOf(dataSnapshot.child("u_aadharcard").getValue(String.class));
                        String pancard = String.valueOf(dataSnapshot.child("u_pancard").getValue(String.class));
                        String birth = String.valueOf(dataSnapshot.child("u_birth").getValue(String.class));




                        //u_mail.setText(mail);
                        u_name.setText(name);
                        u_mobile.setText(mobile);
                        u_mail.setText(email);
                        u_address.setText(address);
                        u_aadhar.setText(aadhar);
                        u_pancard.setText(pancard);
                        u_birth.setText(birth);





                        //  String u_main=String.valueOf(dataSnapshot.child("u_user").getValue());
                        // String u_user=String.valueOf(dataSnapshot.child("u_user").getValue());


                    }
                    else Toast.makeText(UserProfile.this, "Admin Not Exist",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
}
        }
