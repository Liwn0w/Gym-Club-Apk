package edu.bjtu.example.gym_club;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.bmob.v3.BmobUser;

public class profile extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_fit:
                    startActivity(new Intent(profile.this, schedule.class));
                case R.id.navigation_find:
                    startActivity(new Intent(profile.this, find.class));
                    return true;
                case R.id.navigation_news:
                    startActivity(new Intent(profile.this, news.class));
                    return true;
                case R.id.navigation_profile:
                    startActivity(new Intent(profile.this, profile.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BmobUser user = BmobUser.getCurrentUser();

        TextView username = (TextView) findViewById(R.id.profilepage_name);
        username.setText(user.getUsername());

        Button schedulebtn = (Button) findViewById(R.id.myschedule);

        schedulebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this, schedule.class));
            }
        });

        Button diarybtn = (Button) findViewById(R.id.mydiary);

        diarybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this, diary.class));
            }
        });

        Button logout = (Button) findViewById(R.id.settings);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this, MainActivity.class));
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
