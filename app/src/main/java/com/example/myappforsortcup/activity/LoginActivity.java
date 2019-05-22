package com.example.myappforsortcup.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.androidanimations.library.attention.ShakeAnimator;
import com.example.myappforsortcup.R;
import com.example.myappforsortcup.util.SomeUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputName;
    private EditText inputPassword;
    private TextView btnLogin;
    private TextView btnRegister;
    private TextView btnForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_on_login:
                String name = inputName.getText().toString();
                String password = inputPassword.getText().toString();
                if (name.equals("") && password.equals("")){
                    SomeUtil.shakeControl(inputName);
                    SomeUtil.shakeControl(inputPassword);
                }else if (name.equals("") && !password.equals("")){
                    SomeUtil.shakeControl(inputName);
                }else if (!name.equals("") && password.equals("")){
                    SomeUtil.shakeControl(inputPassword);
                }else {
                    Intent intent = new Intent(LoginActivity.this,GravActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.register_on_login:

                break;
            case R.id.forget_on_login:

                break;
        }
    }

    private void initView(){
        inputName = (EditText)findViewById(R.id.input_name_on_login);
        inputPassword = (EditText)findViewById(R.id.input_password_on_login);
        btnLogin = (TextView)findViewById(R.id.login_on_login);
        btnRegister = (TextView)findViewById(R.id.register_on_login);
        btnForget = (TextView)findViewById(R.id.forget_on_login);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnForget.setOnClickListener(this);
    }
}
