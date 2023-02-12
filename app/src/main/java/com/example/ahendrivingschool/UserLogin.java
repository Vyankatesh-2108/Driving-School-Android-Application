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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserLogin extends AppCompatActivity {

    static String uid;
    private EditText u_name;
    private EditText u_password;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_login);

        //get id
        u_name=findViewById(R.id.u_name);
        u_password=findViewById(R.id.u_password);
        mAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressbar);

        progressBar.setVisibility(View.INVISIBLE);

        Button btn=findViewById(R.id.u_loginbtn);
        btn.setOnClickListener(view -> {
            login();
        });



        //move to signup page
        TextView textView = (TextView)findViewById(R.id.userSingUp);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserLogin.this, UserSignUp.class);
                startActivity(intent);
            }
        });

        ImageView imageView = findViewById(R.id.img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserLogin.this, Splash2.class);
                startActivity(intent);
            }
        });


    }


    public  void login(){
        String username=u_name.getText().toString().trim();
        String password=u_password.getText().toString();
        if (TextUtils.isEmpty(username)){
            u_name.setError("");
            u_name.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            u_password.setError("");
            u_password.requestFocus();
        }else {

            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.INVISIBLE);
                                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
                                reference.child(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        if (task.isSuccessful()){
                                            if (task.getResult().exists()){
                                                DataSnapshot dataSnapshot=task.getResult();
                                                String u_user=String.valueOf(dataSnapshot.child("u_user").getValue());
                                                if (u_user.equals("2")){
                                                    Toast.makeText(UserLogin.this, "Authentication success.",
                                                            Toast.LENGTH_SHORT).show();

                                                    Intent intent=new Intent(UserLogin.this,HomePage.class);
                                                    intent.putExtra("email",mAuth.getCurrentUser().getEmail());
                                                    intent.putExtra("uid",mAuth.getCurrentUser().getUid());
                                                    uid = mAuth.getCurrentUser().getUid();
                                                    startActivity(intent);

                                                }else Toast.makeText(UserLogin.this, "User Doesn't Exit",
                                                        Toast.LENGTH_SHORT).show();

                                            }
                                            else Toast.makeText(UserLogin.this, "User Not Exist",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                            } else {
                                u_name.setText("");
                                u_password.setText("");
                                Toast.makeText(UserLogin.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }

    }
}