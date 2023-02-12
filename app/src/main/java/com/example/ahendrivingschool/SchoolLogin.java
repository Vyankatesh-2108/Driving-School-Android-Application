package com.example.ahendrivingschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SchoolLogin extends AppCompatActivity {
    private EditText s_username;
    private EditText s_password;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    Button btn;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_school_login);

        s_username=findViewById(R.id.s_username);
        s_password=findViewById(R.id.s_password);

        mAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressbar);

        progressBar.setVisibility(View.INVISIBLE);

        Button btn=findViewById(R.id.s_loginbtn);
        btn.setOnClickListener(view -> {
            login();
        });


        //move to activity
        TextView textView = (TextView)findViewById(R.id.schoolsignup);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SchoolLogin.this, SchoolSignUp.class);
                startActivity(intent);
            }
        });
        ImageView imageView = findViewById(R.id.img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SchoolLogin.this, Splash2.class);
                startActivity(intent);
            }
        });
    }


    public  void login(){
        String username=s_username.getText().toString().trim();
        String password=s_password.getText().toString();
        if (TextUtils.isEmpty(username)){
            s_username.setError("");
            s_username.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            s_password.setError("");
            s_password.requestFocus();
        }else {
            progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("School");
                                    reference.child(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                                            if (task.isSuccessful()){
                                                    if (task.getResult().exists()){
                                                        DataSnapshot dataSnapshot=task.getResult();
                                                        String s_admin=String.valueOf(dataSnapshot.child("s_admin").getValue());
                                                                if (s_admin.equals("1")){
                                                                    Toast.makeText(SchoolLogin.this, "Authentication success.",
                                                                            Toast.LENGTH_SHORT).show();
                                                                    Intent intent=new Intent(SchoolLogin.this,dummy.class);
                                                                    intent.putExtra("email",mAuth.getCurrentUser().getEmail());
                                                                    intent.putExtra("uid",mAuth.getCurrentUser().getUid());
                                                                    startActivity(intent);
                                                                }else Toast.makeText(SchoolLogin.this, "Admin Doesn't Exit",
                                                                        Toast.LENGTH_SHORT).show();

                                                    }
                                                    else Toast.makeText(SchoolLogin.this, "Admin Not Exist",
                                                            Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });


                                } else {
                                    s_username.setText("");
                                    s_password.setText("");
                                    Toast.makeText(SchoolLogin.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

        }
    }



}