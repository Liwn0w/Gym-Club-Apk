package edu.bjtu.example.gym_club;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import cn.volley.toolbox.ImageLoader;

public class news extends AppCompatActivity {

    CardView c;
    TextView n1,n2,n3;
    ImageView i1,i2,i3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_fit:
                    startActivity(new Intent(news.this, schedule.class));
                case R.id.navigation_find:
                    startActivity(new Intent(news.this, find.class));
                    return true;
                case R.id.navigation_news:
                    startActivity(new Intent(news.this, news.class));
                    return true;
                case R.id.navigation_profile:
                    startActivity(new Intent(news.this, profile.class));
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);



        c = (CardView) findViewById(R.id.cardView);
        n1 = findViewById(R.id.news1);
        i1 = findViewById(R.id.newsimage1);
        n2 = findViewById(R.id.news2);
        i2 = findViewById(R.id.newsimage2);
        n3 = findViewById(R.id.news3);
        i3 = findViewById(R.id.newsimage3);


        BmobQuery<NewsItem> query = new BmobQuery<>();
        query.getObject("1ae0076912", new QueryListener<NewsItem>() {
            @Override
            public void done(NewsItem newsItem, BmobException e) {
                if(e==null) {
                    i1.setImageBitmap(getBitmapFromURL(newsItem.getIconUrl()));
                }
            }
        });

        //Add some news first
        /*
        final BmobFile file = new BmobFile("baseball",null,"baseball.jpg");
        file.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                NewsItem n = new NewsItem();
                n.setContent("New baseball coach has arrived!");
                n.setPicture(file);
                n.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        Toast.makeText(news.this, "Sucessfull upload", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        final BmobFile file1 = new BmobFile("basketball",null,"basketball.jpg");

        file1.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                NewsItem n = new NewsItem();
                n.setContent("Basketball team is looking for new members!");
                n.setPicture(file1);
                n.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        Toast.makeText(news.this, "Sucessfull upload", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        final BmobFile file2 = new BmobFile("Stepdance", null, "stepdance.jpg");
        file2.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                NewsItem n = new NewsItem();
                n.setContent("New course in stepdancing has opened!");
                n.setPicture(file2);
                n.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null) Toast.makeText(news.this, "Sucessfull upload", Toast.LENGTH_SHORT).show();
                        else {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

*/



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

}
