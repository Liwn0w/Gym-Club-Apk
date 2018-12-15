package edu.bjtu.example.gym_club;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private Button signupBtn;

    @Override
    protected void onRestart(){
        super.onRestart();
        loginBtn.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_shape));
        signupBtn.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_shape));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        Bmob.initialize(this,"17d176992b0a1939358cf9fb57bf0d66");

        setContentView(R.layout.activity_main);
        loginBtn = (Button) this.findViewById(R.id.mainbtn_login);
        signupBtn = (Button) this.findViewById(R.id.mainbtn_sign);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBtn.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_shape_change));
                Intent intent = new Intent(MainActivity.this, login_page.class);

                startActivity(intent);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupBtn.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_shape_change));
                Intent intent = new Intent(MainActivity.this, sign_page.class);

                startActivity(intent);
            }
        });
    }
}
