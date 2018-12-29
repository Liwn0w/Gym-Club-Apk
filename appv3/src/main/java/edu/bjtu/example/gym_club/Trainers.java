package edu.bjtu.example.gym_club;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Trainers extends AppCompatActivity {

        private Button call, sms, email;
        private TextView tlfno, emailaddress;
        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_fit:
                        startActivity(new Intent(Trainers.this, schedule.class));
                        return true;
                    case R.id.navigation_find:
                        startActivity(new Intent(Trainers.this, find.class));
                        return true;
                    case R.id.navigation_news:
                        startActivity(new Intent(Trainers.this, news.class));
                        return true;
                    case R.id.navigation_profile:
                        startActivity(new Intent(Trainers.this, profile.class));
                        return true;
                }
                return false;
            }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_trainers);
            call = (Button) findViewById(R.id.callaction);
            tlfno = (TextView) findViewById(R.id.tlf);

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phone = "+34666777888";
                    Intent callIntent = new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel", phone,null));
                    startActivity(callIntent);
                }
            });

        }
    }

