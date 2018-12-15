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

public class sign_page extends AppCompatActivity implements View.OnClickListener {

    EditText username, password, repeatpassword, email, personname;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_page);
        login = (Button) findViewById(R.id.signpage_login);
        username = (EditText) findViewById(R.id.signpage_username);
        password = (EditText) findViewById(R.id.signpage_password);
        email = (EditText) findViewById(R.id.signpage_email);
        personname = (EditText) findViewById(R.id.signpage_name);
        repeatpassword = (EditText) findViewById(R.id.signpage_repeat_password);
        login.setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {
        String user_username=username.getText().toString();
        String user_password = password.getText().toString().trim();
        String user_repeatpassword = repeatpassword.getText().toString().trim();
        String user_email = email.getText().toString();
        String person_name = personname.getText().toString();

        if(user_username.isEmpty() || user_password.isEmpty() || user_email.isEmpty() || person_name.isEmpty()) {
            Toast.makeText(sign_page.this, "Please fill out everything", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!user_password.equals(user_repeatpassword)) {
            Toast.makeText(this, "Two passwords don't match", Toast.LENGTH_SHORT).show();
            return;
        }

        User user1 = new User();
        user1.setUsername(user_username);
        user1.setPassword(user_password);
        user1.setEmail(user_email);
        user1.setName(person_name);

        user1.signUp(new SaveListener<BmobUser>() {

            @Override
            public void done(BmobUser objectId, BmobException e) {
                if(e==null) {
                    Toast.makeText(sign_page.this, "Succeeded to Sign Up", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(sign_page.this, profile.class));
                } else {
                    Toast.makeText(sign_page.this, "Failed to Sign Up", Toast.LENGTH_SHORT).show();
                }
            }
        });

        finish();
    }
}
