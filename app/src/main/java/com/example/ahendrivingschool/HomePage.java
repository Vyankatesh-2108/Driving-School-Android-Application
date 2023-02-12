package com.example.ahendrivingschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.ahendrivingschool.databinding.ActivityHomePageBinding;
import com.example.ahendrivingschool.databinding.ActivityMainBinding;

public class HomePage extends AppCompatActivity {

    ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home_page);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new recfragment()).commit();

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new recfragment());


        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.bottom_home:
                    replaceFragment(new recfragment());
                    break;
                case R.id.bottom_note:
                    replaceFragment(new NotePage());
                    break;
                case R.id.bottom_money:
                    replaceFragment(new MoneyPage());
                    break;
                case R.id.bottom_profile:
                    replaceFragment(new Settings());
                    break;

            }


            return  true;
        });


    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.wrapper,fragment);
        fragmentTransaction.commit();

    }

}