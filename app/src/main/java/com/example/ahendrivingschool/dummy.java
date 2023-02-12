package com.example.ahendrivingschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class dummy extends AppCompatActivity {

    TextView textView,textView2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        button=findViewById(R.id.button);

        textView.setText(getIntent().getStringExtra("email").toString());
        textView2.setText(getIntent().getStringExtra("uid").toString());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(dummy.this,UserLogin.class));
                finish();
            }
        });
    }

}