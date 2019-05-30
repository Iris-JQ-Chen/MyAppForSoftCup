package com.example.myappforsortcup.animationTest;

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

import com.example.myappforsortcup.R;
import com.example.myappforsortcup.activity.MainActivity;
import com.example.myappforsortcup.activity.RegisterActivity;
import com.example.myappforsortcup.util.SomeUtil;
import com.jaouan.revealator.Revealator;
import com.jaouan.revealator.animations.AnimationListenerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.fab) View mFab;
    @BindView(R.id.plane) View mPlaneImageView;
    @BindView(R.id.plane_layout) View mPlaneLayout;
    @BindView(R.id.inputs_layout) View mInputsLayout;
    @BindView(R.id.sky_layout) View mSkyLayout;
    @BindView(R.id.sent_layout) View mSentLayout;
    @BindView(R.id.check) View mCheckImageView;

    @BindView(R.id.input_name_on_login) EditText inputName;
    @BindView(R.id.input_password_on_login)EditText inputPassword;
//    @BindView(R.id.login_on_login)TextView btnLogin;
//    @BindView(R.id.register_on_login)LinearLayout btnRegister;
//    @BindView(R.id.forget_on_login)TextView btnForget;

    private Intent intent = null;

    public static final String prefs_File = "user";
    public static final String is_Register = "isRegister";
    public static final String user_Name = "userName";
    public static final String user_Password = "userPassword";
    public static final String user_Email = "userEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initUserInfo();
        intent = new Intent(LoginActivity.this,MainActivity.class);
        setupWindowAnimations();
    }

    @OnClick(R.id.login_on_login)
    void login(){
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
            SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.prefs_File, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(LoginActivity.is_Register,true);
            editor.putString(LoginActivity.user_Name,name);
            editor.putString(LoginActivity.user_Password,password);
            editor.commit();
            send();
        }
    }

    @OnClick(R.id.register_on_login)
    void register(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.forget_on_login)
    void ForgetPassword(){

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
