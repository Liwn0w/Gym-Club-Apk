package edu.bjtu.example.gym_club;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Vector;

public class find extends AppCompatActivity {

    RecyclerView recyclerView;
    Vector<YouTubeVideos> youTubeVideos = new Vector<>();
    Button trainer;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_fit:
                    startActivity(new Intent(find.this, schedule.class));
                    return true;
                case R.id.navigation_find:
                    startActivity(new Intent(find.this, find.class));
                    return true;
                case R.id.navigation_news:
                    startActivity(new Intent(find.this, news.class));
                    return true;
                case R.id.navigation_profile:
                    startActivity(new Intent(find.this, profile.class));
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        trainer = findViewById(R.id.trainers);

        trainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(find.this, Trainers.class));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //youtubevideos

        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/v7AYKMP6rOE\" frameborder=\"0\" allowfullscreen></iframe>"));

        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/v7AYKMP6rOE\" frameborder=\"0\" allowfullscreen></iframe>"));

        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/v7AYKMP6rOE\" frameborder=\"0\" allowfullscreen></iframe>"));

        VideoAdapter videoAdapter = new VideoAdapter(youTubeVideos);

        recyclerView.setAdapter(videoAdapter);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
