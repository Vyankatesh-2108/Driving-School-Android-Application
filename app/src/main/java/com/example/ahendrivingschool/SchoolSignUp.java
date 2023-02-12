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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SchoolSignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference reference;

    EditText s_name;
    EditText s_password;
    EditText s_confirmpassword;
    EditText s_address;
    EditText s_pincode;
    EditText s_mail;
    EditText s_mobile;
    Button s_signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_school_sign_up);

        s_name = findViewById(R.id.s_name);
        s_password = findViewById(R.id.s_password);
        s_mail = findViewById(R.id.s_mail);
        s_confirmpassword = findViewById(R.id.s_confirmpassword);
        s_mobile = findViewById(R.id.s_mobile);
        s_pincode = findViewById(R.id.s_pincode);
        s_address=findViewById(R.id.s_address);
        s_signupbtn = (Button) findViewById(R.id.s_signupbtn);

        mAuth = FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("School");


        s_signupbtn.setOnClickListener(view -> {
            sclsignup();
        });


        //move activity
        TextView textView = (TextView)findViewById(R.id.schoollogin);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SchoolSignUp.this, SchoolLogin.class);
                startActivity(intent);
            }
        });

        ImageView imageView = findViewById(R.id.img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SchoolSignUp.this, SchoolLogin.class);
                startActivity(intent);
            }
        });

    }

    public void sclsignup() {
        String schoolname = s_name.getText().toString().trim();
        String mail = s_mail.getText().toString().trim();
        String mobile = s_mobile.getText().toString().trim();
        String password = s_password.getText().toString().trim();
        String confirmPass = s_confirmpassword.getText().toString().trim();
        String pincode = s_pincode.getText().toString().trim();
        String address = s_address.getText().toString().trim();

        if (TextUtils.isEmpty(schoolname)){
            s_name.setError("");
            s_name.requestFocus();

        }else if (TextUtils.isEmpty(mail)) {

            s_mail.setError("");
            s_mail.requestFocus();
        }else if (TextUtils.isEmpty(mobile)) {

            s_mobile.setError("");
            s_mobile.requestFocus();
        }else if (TextUtils.isEmpty(password)) {

            s_password.setError("");
            s_password.requestFocus();
        }
        else if (TextUtils.isEmpty(confirmPass)) {

            s_confirmpassword.setError("");
            s_confirmpassword.requestFocus();

        }else if (TextUtils.isEmpty(pincode)) {

            s_pincode.setError("");
            s_pincode.requestFocus();
        }else if (TextUtils.isEmpty(address)) {

            s_address.setError("");
            s_address.requestFocus();
        }else{

            if (password.equals(confirmPass)){
                //  progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    //progressBar.setVisibility(View.INVISIBLE);
                                    String admin ="admin";
                                    SchoolDataHolder obj=new SchoolDataHolder(schoolname,address,pincode,mail,mobile,"1");
                                    reference.child(mAuth.getCurrentUser().getUid()).setValue(obj);

                                    Toast.makeText(SchoolSignUp.this, "Authentication successed.",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SchoolSignUp.this,HomePage.class));
                                    finish();

                                } else {
                                    s_name.setText("");
                                    s_pincode.setText("");
                                    s_address.setText("");
                                    s_confirmpassword.setText("");
                                    s_mail.setText("");
                                    s_mobile.setText("");
                                    s_password.setText("");
                                    //progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(SchoolSignUp.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                }
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SchoolSignUp.this,e.toString(),Toast.LENGTH_LONG).show();
                            }
                        });
            }
            else{
                Toast.makeText(SchoolSignUp.this,"password not matched",Toast.LENGTH_LONG).show();
            }


        }
    }

}