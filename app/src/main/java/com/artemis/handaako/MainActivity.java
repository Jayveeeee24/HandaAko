package com.artemis.handaako;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.artemis.handaako.databinding.ActivityMainBinding;
import com.artemis.handaako.fragments.EmergencyFragment;
import com.artemis.handaako.fragments.HomeFragment;
import com.artemis.handaako.fragments.LearnFragment;
import com.artemis.handaako.fragments.QuizFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {

    SmoothBottomBar smoothBottomBar;
    FloatingActionButton fab_siren;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_siren = findViewById(R.id.fab_emergency);
        fab_siren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SirenActivity.class);
                startActivity(intent);
            }
        });
        smoothBottomBar = findViewById(R.id.bottomNavigationView);
        replaceFragment(new HomeFragment(smoothBottomBar));
        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                if (i == 0){
                    replaceFragment(new HomeFragment(smoothBottomBar));
                }
                if (i == 1){
                    replaceFragment(new LearnFragment());
                }
                if (i == 2){
                    replaceFragment(new QuizFragment());
                }
                if (i == 3){
                    replaceFragment(new EmergencyFragment());
                }
                return false;
            }
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}