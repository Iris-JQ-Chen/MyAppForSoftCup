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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myappforsortcup.R;
import com.example.myappforsortcup.animationTest.LoginActivity;
import com.example.myappforsortcup.util.SomeUtil;
import com.jaouan.revealator.Revealator;
import com.jaouan.revealator.animations.AnimationListenerAdapter;
import com.mob.MobSDK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class RegisterActivity extends AppCompatActivity implements PlatformActionListener {

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
    @BindView(R.id.qq_on_register) ImageButton qqRegisterBtn;
    @BindView(R.id.weixin_on_register) ImageButton weixinRegisterBtn;
    @BindView(R.id.weibo_on_register) ImageButton weiboRegisterBtn;
//    @BindView(R.id.login_on_register)TextView btnLogin;
//    @BindView(R.id.register_on_register)LinearLayout btnRegister;

    private Intent intent = null;
    private PlatformDb platDB; //平台授权数据DB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        MobSDK.init(this);
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

    @OnClick(R.id.qq_on_register)
    void qqRegister(){
//        Platform plat = ShareSDK.getPlatform(QQ.NAME);
//        plat.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
//        plat.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
//        plat.setPlatformActionListener(this);//授权回调监听，监听oncomplete，onerror，oncancel三种状态
//        if(!plat.isClientValid()){
//            //判断是否存在授权凭条的客户端，true是有客户端，false是无
//            Toast.makeText(RegisterActivity.this,"尚未安装QQ客户端",Toast.LENGTH_SHORT).show();
//        }
//        if(plat.isAuthValid()){
//            //判断是否已经存在授权状态，可以根据自己的登录逻辑设置
//            Toast.makeText(this, "已经授权过了", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        ShareSDK.setActivity(this);//抖音登录适配安卓9.0
//        plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        authorize(qq);
    }

    @OnClick(R.id.weixin_on_register)
    void weixinRegister(){
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        authorize(wechat);
    }

    @OnClick(R.id.weibo_on_register)
    void weiboRegister(){
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        authorize(weibo);
    }

    //第三方授权登录
    private void authorize(Platform plat) {

        if (plat == null) {
            return;
        }


        // true不使用SSO授权，false使用SSO授权
        plat.SSOSetting(false);
        plat.setPlatformActionListener(this);
        plat.authorize();
        //获取用户资料
        plat.showUser(null);

        //判断指定平台是否已经完成授权
        if(plat.isAuthValid()) {
            String token = plat.getDb().getToken();
            String userId = plat.getDb().getUserId();
            String name = plat.getDb().getUserName();
            String gender = plat.getDb().getUserGender();
            String headImageUrl = plat.getDb().getUserIcon();
            String platformNname = plat.getDb().getPlatformNname();
            if (userId != null) {

                //已经授权过，直接下一步操作
                if(platformNname.equals(SinaWeibo.NAME)) {
                    //微博授权
                } else if(platformNname.equals(QQ.NAME)) {
                    //QQ授权
                } else if(platformNname.equals(Wechat.NAME)) {
                    //微信授权
                }
                return;
            }
        }

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

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Toast.makeText(RegisterActivity.this,"onComplete",Toast.LENGTH_SHORT).show();
        String headImageUrl = null;//头像
        String userId;//userId
        String token;//token
        String gender;//性别
        String name = null;//用户名

        if (i == Platform.ACTION_USER_INFOR) {
            Toast.makeText(RegisterActivity.this,"ACTION_USER_INFOR",Toast.LENGTH_SHORT).show();

            platDB = platform.getDb(); // 获取平台数据DB

            if (platform.getName().equals(Wechat.NAME)) {
                //微信登录

                // 通过DB获取各种数据
                token = platDB.getToken();
                userId = platDB.getUserId();
                name = platDB.getUserName();
                gender = platDB.getUserGender();
                headImageUrl = platDB.getUserIcon();
                if ("m".equals(gender)) {
                    gender = "1";
                } else {
                    gender = "2";
                }

                Toast.makeText(RegisterActivity.this,token+"\n"+userId+"\n"+name+"\n"+gender+"\n"+headImageUrl,Toast.LENGTH_SHORT).show();

            } else if (platform.getName().equals(SinaWeibo.NAME)) {
                // 微博登录

                token = platDB.getToken();
                userId = platDB.getUserId();
                name = hashMap.get("nickname").toString(); // 名字
                gender = hashMap.get("gender").toString(); // 年龄
                headImageUrl = hashMap.get("figureurl_qq_2").toString(); // 头像figureurl_qq_2 中等图，figureurl_qq_1缩略图
                Toast.makeText(RegisterActivity.this,token+"\n"+userId+"\n"+name+"\n"+gender+"\n"+headImageUrl,Toast.LENGTH_SHORT).show();

            } else if (platform.getName().equals(QQ.NAME)) {
                // QQ登录
                Toast.makeText(RegisterActivity.this,"QQ.NAME",Toast.LENGTH_SHORT).show();

                token = platDB.getToken();
                userId = platDB.getUserId();
                name = hashMap.get("nickname").toString(); // 名字
                gender = hashMap.get("gender").toString(); // 年龄
                headImageUrl = hashMap.get("figureurl_qq_2").toString(); // 头像figureurl_qq_2 中等图，figureurl_qq_1缩略图
                Toast.makeText(RegisterActivity.this,token+"\n"+userId+"\n"+name+"\n"+gender+"\n"+headImageUrl,Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }

    @Override
    protected void onDestroy() {
//        ShareSDK.unregisterPlatform(platDB);//平台注销
        super.onDestroy();
    }
}
