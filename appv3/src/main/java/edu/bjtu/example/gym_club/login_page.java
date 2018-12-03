package edu.bjtu.example.gym_club;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import edu.bjtu.example.gym_club.R;

public class login_page extends AppCompatActivity implements View.OnClickListener {

    private EditText username, password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        login = (Button) findViewById(R.id.logpage_login);
        username = (EditText) findViewById(R.id.loginpage_username);
        password = (EditText) findViewById(R.id.loginpage_password);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String user_username=username.getText().toString();
        String user_password = password.getText().toString().trim();

        if(user_username.isEmpty() || user_password.isEmpty()) {
            Toast.makeText(login_page.this, "Please fill out email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        User user1 = new User();
        user1.setUsername(user_username);
        user1.setPassword(user_password);

        user1.login(new SaveListener<BmobUser>() {

            @Override
            public void done(BmobUser objectId, BmobException e) {
                if(e==null) {
                    Toast.makeText(login_page.this, "Succeeded to login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login_page.this, profile.class));
                } else {
                    Toast.makeText(login_page.this, "Password or Username is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        finish();
    }
}
