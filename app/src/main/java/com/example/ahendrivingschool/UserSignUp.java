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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserSignUp extends AppCompatActivity {

   // static String uid;

    private FirebaseAuth mAuth;
    DatabaseReference reference;   //firebase.getIntance.getREfererece

    //autherntication
    EditText u_name;
    EditText u_password;
    EditText u_confirmpassword;
    ProgressBar progressBar;


    //real time data
    EditText u_mobile;
    EditText u_mail;
    EditText u_birth;

    EditText u_address;
    EditText  u_aadharcard;
    EditText u_pancard;

    Button u_signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_user_sign_up);

        u_name = findViewById(R.id.u_name);
        u_password = findViewById(R.id.u_password);
        u_mobile = findViewById(R.id.u_mobile);
        u_mail = findViewById(R.id.u_mail);
        u_birth = findViewById(R.id.u_birth);
        u_address = findViewById(R.id.u_address);
        u_aadharcard = findViewById(R.id.u_aadharcard);
        u_pancard = findViewById(R.id.u_pancard);


        u_confirmpassword = findViewById(R.id.u_confirmpassword);
        u_signupbtn = (Button) findViewById(R.id.u_signupbtn);
        progressBar=findViewById(R.id.progressbar);


        ImageView imageView = findViewById(R.id.img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserSignUp.this, UserLogin.class);
                startActivity(intent);
            }
        });

        TextView signup=findViewById(R.id.userlogin);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserSignUp.this, UserLogin.class);
                startActivity(intent);
                finish();
            }
        });

        //authentication
        mAuth = FirebaseAuth.getInstance();
        reference=FirebaseDatabase.getInstance().getReference("Users");

        u_signupbtn.setOnClickListener(view -> {
            createUser();
        });


    }
    //    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser ==null){
//            startActivity(new Intent(UserSignUp.this,UserLogin.class));
//        }
//
//    }
    public void createUser() {

        String username = u_name.getText().toString().trim();
        String mail = u_mail.getText().toString().trim();
        String mobile = u_mobile.getText().toString().trim();
        String address = u_address.getText().toString().trim();
        String aadharcard = u_aadharcard.getText().toString().trim();
        String pancard = u_pancard.getText().toString().trim();
        String password = u_password.getText().toString().trim();
        String confirmPass = u_confirmpassword.getText().toString().trim();
        String birthDate = u_birth.getText().toString().trim();

        if (TextUtils.isEmpty(username)){
            u_name.setError("");
            u_name.requestFocus();

        }else if (TextUtils.isEmpty(mail)) {

            u_mail.setError("");
            u_mail.requestFocus();
        }else if (TextUtils.isEmpty(mobile)) {

            u_mobile.setError("");
            u_mail.requestFocus();
        }else if (TextUtils.isEmpty(address)) {

            u_address.setError("");
            u_address.requestFocus();
        }else if (TextUtils.isEmpty(aadharcard)) {

            u_aadharcard.setError("");
            u_aadharcard.requestFocus();
        }else if (TextUtils.isEmpty(pancard)) {

            u_pancard.setError("");
            u_pancard.requestFocus();
        }

        else if (TextUtils.isEmpty(password)) {

            u_password.setError("");
            u_password.requestFocus();
        }
        else if (TextUtils.isEmpty(confirmPass)) {

            u_confirmpassword.setError("");
            u_confirmpassword.requestFocus();

        }else if (TextUtils.isEmpty(birthDate)) {

            u_birth.setError("");
            u_birth.requestFocus();
        }else{

            if (password.equals(confirmPass)){
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    String user="2";
                                    UserDataHolder obj=new UserDataHolder(username, mail, birthDate,mobile,user,address,aadharcard,pancard);
                                    reference.child(mAuth.getCurrentUser().getUid()).setValue(obj);

                                    Toast.makeText(UserSignUp.this, "Authentication successed.",Toast.LENGTH_SHORT).show();
                                    UserLogin.uid = mAuth.getCurrentUser().getUid();
                                    startActivity(new Intent(UserSignUp.this,HomePage.class));
                                    finish();

                                } else {
                                    u_name.setText("");
                                    u_birth.setText("");
                                    u_confirmpassword.setText("");
                                    u_mail.setText("");
                                    u_mobile.setText("");
                                    u_password.setText("");
                                    u_address.setText("");
                                    u_aadharcard.setText("");
                                    u_pancard.setText("");
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(UserSignUp.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                }
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UserSignUp.this,e.toString(),Toast.LENGTH_LONG).show();
                            }
                        });
            }
            else{
                Toast.makeText(UserSignUp.this,"password not matched",Toast.LENGTH_LONG).show();
            }


        }
    }
}