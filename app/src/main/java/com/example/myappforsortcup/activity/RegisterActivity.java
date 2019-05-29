package com.example.myappforsortcup.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappforsortcup.R;
import com.example.myappforsortcup.activity.SearchActivity;
import com.example.myappforsortcup.animationTest.LoginActivity;
import com.example.myappforsortcup.util.SomeUtil;
import com.jaouan.revealator.Revealator;
import com.jaouan.revealator.animations.AnimationListenerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.fab) View mFab;
    @BindView(R.id.plane) View mPlaneImageView;
    @BindView(R.id.plane_layout) View mPlaneLayout;
    @BindView(R.id.inputs_layout) View mInputsLayout;
    @BindView(R.id.sky_layout) View mSkyLayout;
    @BindView(R.id.sent_layout) View mSentLayout;
    @BindView(R.id.check) View mCheckImageView;

    @BindView(R.id.input_name_on_register) EditText inputName;
    @BindView(R.id.input_password_on_register)EditText inputPassword;
    @BindView(R.id.input_email_on_register)EditText inputEmail;
//    @BindView(R.id.login_on_register)TextView btnLogin;
//    @BindView(R.id.register_on_register)LinearLayout btnRegister;

    private Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initUserInfo();
        intent = new Intent(RegisterActivity.this,MainActivity.class);
        setupWindowAnimations();
    }

    @OnClick(R.id.login_on_register)
    void login(){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.register_on_register)
    void register(){
//        String name = inputName.getText().toString();
//        String password = inputPassword.getText().toString();
//        String email = inputEmail.getText().toString();
//        List<String> list = new ArrayList<>();
//        list.add(name);
//        list.add(password);
//        list.add(email);
//
//        if (name.equals("") && password.equals("")){
//            SomeUtil.shakeControl(inputName);
//            SomeUtil.shakeControl(inputPassword);
//        }else if (name.equals("") && !password.equals("")){
//            SomeUtil.shakeControl(inputName);
//        }else if (!name.equals("") && password.equals("")){
//            SomeUtil.shakeControl(inputPassword);
//        }else {
//            //网络
//            send();
//        }
        boolean isEmpty = false;
        List<EditText> list = new ArrayList<>();
        list.add(inputName);
        list.add(inputPassword);
        list.add(inputEmail);
        for (EditText e: list){
            if (e.getText().toString().equals("")){
                SomeUtil.shakeControl(e);
                isEmpty = true;
            }
        }
        if (!isEmpty){
            SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.prefs_File, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(LoginActivity.is_Register,true);
            editor.putString(LoginActivity.user_Name,inputName.getText().toString());
            editor.putString(LoginActivity.user_Password,inputPassword.getText().toString());
            editor.putString(LoginActivity.user_Email,inputEmail.getText().toString());
            editor.commit();
            send();
        }
    }

    @OnClick(R.id.fab)
    void send() {
        // - Prepare views visibility.
        mCheckImageView.setVisibility(View.INVISIBLE);
        mSentLayout.setVisibility(View.INVISIBLE);
        mFab.setVisibility(View.VISIBLE);

        // - Rotate fab.
        final RotateAnimation rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateAnimation.setDuration(280);
        mFab.startAnimation(rotateAnimation);

        // - Hide inputs layout.
        final Animator circularReveal = ViewAnimationUtils.createCircularReveal(mInputsLayout, (int) (mFab.getX() + mFab.getWidth() / 2), (int) (mFab.getY() + mFab.getHeight() / 2), mInputsLayout.getHeight(), 0);
        circularReveal.setDuration(250);
        circularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // - Update views visibility.
                mInputsLayout.setVisibility(View.INVISIBLE);

                // - Fly away.
                flyAway();
            }
        });
        circularReveal.start();

    }

    private void initUserInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.prefs_File,Context.MODE_PRIVATE);
        if (sharedPreferences != null && sharedPreferences.getBoolean(LoginActivity.is_Register,false)){
            inputName.setText(sharedPreferences.getString(LoginActivity.user_Name,""));
            inputPassword.setText(sharedPreferences.getString(LoginActivity.user_Password,""));
        }
    }

    private void flyAway() {
        // - Combine rotation and translation animations.
        final RotateAnimation rotateAnimation = new RotateAnimation(0, 180);
        rotateAnimation.setDuration(1000);
        mPlaneImageView.startAnimation(rotateAnimation);
        Revealator.reveal(mSentLayout)
                .from(mPlaneLayout)
                .withTranslateDuration(1000)
                .withCurvedTranslation(new PointF(-1200, 0))
                .withRevealDuration(200)
                .withHideFromViewAtTranslateInterpolatedTime(.5f)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {

                        // - Display checked icon.
                        final ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
                        scaleAnimation.setInterpolator(new BounceInterpolator());
                        scaleAnimation.setDuration(500);
                        scaleAnimation.setAnimationListener(new AnimationListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animation animation) {
//                                mInputsLayout.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//
//                                        // - Restore inputs layout.
//                                        retoreInputsLayout();
//
//                                    }
//                                }, 1000);

//                                Intent intent = new Intent(this,GravActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        mCheckImageView.startAnimation(scaleAnimation);
                        mCheckImageView.setVisibility(View.VISIBLE);

                    }
                }).start();

    }

    private void setupWindowAnimations(){
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);

        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
    }

}
