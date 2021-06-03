package com.cahyaa.uas_2020_2_pt_lemburanku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BotnavActivity extends AppCompatActivity {

    private BottomNavigationView botnav_botnavbar;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botnav);
        initView();
        setBottomNavBar();
    }

    private void setBottomNavBar(){
        botnav_botnavbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if(item.getItemId() == R.id.menu_page1){
                    selectedFragment = new BerandaFragment();
//                }else if(item.getItemId() == R.id.menu_page2){
//                    selectedFragment = new CatatanFragment();
                }else if(item.getItemId() == R.id.menu_page3){
                    selectedFragment = new RumusFragment();
                }else if(item.getItemId() == R.id.menu_page4) {
                    selectedFragment = new TentangFragment();
                }

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.botnav_framelayout, selectedFragment).commit();

                return true;
            }
        });
    }

    private void initView(){
        botnav_botnavbar = findViewById(R.id.botnav_botnavbar);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.botnav_framelayout, new BerandaFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Double click to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}