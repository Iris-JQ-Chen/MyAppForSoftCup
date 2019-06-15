package com.example.myappforsortcup.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.myappforsortcup.R;
import com.example.myappforsortcup.animationTest.LoginActivity;
import com.example.myappforsortcup.util.SomeUtil;
import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnGestureListener,View.OnClickListener {

    private static final int REQUEST_CODE_CHOOSE = 23;

    private Drawer drawer;
    private IProfile profile;
    private AccountHeader header;
    private Toolbar toolbar;
    private GestureDetector detector;
    private ViewFlipper flipper;
    ImageView []iamges=new ImageView[2];
    int i = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDrawer();
        setupWindowAnimations();

        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5d04cbc4");

    }

    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_drawer_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer();
            }
        });
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));

        findViewById(R.id.search_btn_on_main).setOnClickListener(this);

        iamges[0]=(ImageView) findViewById(R.id.image_key_on_main);
        iamges[1]=(ImageView) findViewById(R.id.image_description_on_main);
        iamges[0].setOnClickListener(this);
        iamges[1].setOnClickListener(this);

        detector = new GestureDetector(this);
        flipper = (ViewFlipper) this.findViewById(R.id.view_flipper_on_main);
        flipper.addView(LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_by_key,null,false));
        flipper.addView(LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_by_description,null,false));

    }

    private void initDrawer(){
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.prefs_File, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(LoginActivity.is_Register,false)){
            profile = new ProfileDrawerItem()
                    .withName(sharedPreferences.getString(LoginActivity.user_Name,getResources().getString(R.string.username)))
                    .withEmail(sharedPreferences.getString(LoginActivity.user_Email,getResources().getString(R.string.useremail)))
                    .withIcon("http://noavatar.csdn.net/5/D/8/1_soma5431.jpg")
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            return false;
                        }
                    })
                    .withIdentifier(100);
        }else {
            profile = new ProfileDrawerItem()
                    .withName(getResources().getString(R.string.username))
                    .withEmail(getResources().getString(R.string.useremail))
                    .withIcon("http://noavatar.csdn.net/5/D/8/1_soma5431.jpg")
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            return false;
                        }
                    })
                    .withIdentifier(100);
        }
        header = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(profile)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        switch ((int)profile.getIdentifier()) {
                            case 100:
                                changeProfileImage();
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                })
                .build();
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(header)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.collection_on_drawer)
                                .withIcon(R.drawable.ic_collections_bookmark_black_48dp)
                                .withIdentifier(1)
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Intent intent = new Intent(MainActivity.this,CollectionActivity.class);
                                        startActivity(intent);
                                        return true;
                                    }
                                })
                                .withSelectable(false),
                        new PrimaryDrawerItem()
                                .withName(R.string.history_on_drawer)
                                .withIcon(R.drawable.ic_history_black_48dp)
                                .withIdentifier(2)
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
                                        startActivity(intent);
                                        return true;
                                    }
                                })
                                .withSelectable(false),
                        new PrimaryDrawerItem()
                                .withName(R.string.change_info_on_drawer)
                                .withIcon(R.drawable.ic_account_circle_black_24dp)
                                .withIdentifier(3)
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Intent intent = new Intent(MainActivity.this,ChangeInfoActivity.class);
                                        startActivity(intent);
                                        return true;
                                    }
                                })
                                .withSelectable(false),
                        new SectionDrawerItem().withName(R.string.setting_on_drawer),  //分组item，类似于group标签，无点击效果
                        new SwitchDrawerItem()  //添加带有switch开关的item
                                .withName(R.string.change_language_on_drawer)
                                .withIcon(R.drawable.ic_language_black_48dp)
                                .withIdentifier(4)
                                .withCheckable(false)
                                .withOnCheckedChangeListener(new OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
                                        Toast.makeText(MainActivity.this,"3"+isChecked,Toast.LENGTH_SHORT).show();
                                    }
                                }),
                        new SwitchDrawerItem()
                                .withName(R.string.change_theme_color_on_drawer)
                                .withIcon(R.drawable.ic_invert_colors_black_48dp)
                                .withIdentifier(5)
                                .withOnCheckedChangeListener(new OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
                                        Toast.makeText(MainActivity.this,"4"+isChecked,Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .withChecked(false)  //设置默认为ON状态
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        //监听方法实现
                                        Toast.makeText(MainActivity.this,"The DrawerItem is clicked",Toast.LENGTH_SHORT).show();
                                        return false;
                                    }
                                }),
                        new SecondaryDrawerItem().withName(R.string.other_on_drawer),
                        new PrimaryDrawerItem()
                                .withName(R.string.login_out_on_drawer)
                                .withIcon(R.drawable.ic_error_outline_black_48dp)
                                .withIdentifier(6)
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                        return false;
                                    }
                                })
                ).build();
    }

    private void initDrawer(Bitmap bitmap){
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.prefs_File, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(LoginActivity.is_Register,false)){
            profile = new ProfileDrawerItem()
                    .withName(sharedPreferences.getString(LoginActivity.user_Name,getResources().getString(R.string.username)))
                    .withEmail(sharedPreferences.getString(LoginActivity.user_Email,getResources().getString(R.string.useremail)))
                    .withIcon(bitmap)
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            return false;
                        }
                    })
                    .withIdentifier(100);
        }else {
            profile = new ProfileDrawerItem()
                    .withName(getResources().getString(R.string.username))
                    .withEmail(getResources().getString(R.string.useremail))
                    .withIcon(bitmap)
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            return false;
                        }
                    })
                    .withIdentifier(100);
        }
        header = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(profile)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        switch ((int)profile.getIdentifier()) {
                            case 100:
                                changeProfileImage();
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                })
                .build();
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(header)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.collection_on_drawer)
                                .withIcon(R.drawable.ic_collections_bookmark_black_48dp)
                                .withIdentifier(1)
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Intent intent = new Intent(MainActivity.this,CollectionActivity.class);
                                        startActivity(intent);
                                        return true;
                                    }
                                })
                                .withSelectable(false),
                        new PrimaryDrawerItem()
                                .withName(R.string.history_on_drawer)
                                .withIcon(R.drawable.ic_history_black_48dp)
                                .withIdentifier(2)
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
                                        startActivity(intent);
                                        return true;
                                    }
                                })
                                .withSelectable(false),
                        new PrimaryDrawerItem()
                                .withName(R.string.change_info_on_drawer)
                                .withIcon(R.drawable.ic_account_circle_black_24dp)
                                .withIdentifier(3)
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Intent intent = new Intent(MainActivity.this,ChangeInfoActivity.class);
                                        startActivity(intent);
                                        return true;
                                    }
                                })
                                .withSelectable(false),
                        new SectionDrawerItem().withName(R.string.setting_on_drawer),  //分组item，类似于group标签，无点击效果
                        new SwitchDrawerItem()  //添加带有switch开关的item
                                .withName(R.string.change_language_on_drawer)
                                .withIcon(R.drawable.ic_language_black_48dp)
                                .withIdentifier(4)
                                .withCheckable(false)
                                .withOnCheckedChangeListener(new OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
                                        Toast.makeText(MainActivity.this,"3"+isChecked,Toast.LENGTH_SHORT).show();
                                    }
                                }),
                        new SwitchDrawerItem()
                                .withName(R.string.change_theme_color_on_drawer)
                                .withIcon(R.drawable.ic_invert_colors_black_48dp)
                                .withIdentifier(5)
                                .withOnCheckedChangeListener(new OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
                                        Toast.makeText(MainActivity.this,"4"+isChecked,Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .withChecked(false)  //设置默认为ON状态
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        //监听方法实现
                                        Toast.makeText(MainActivity.this,"The DrawerItem is clicked",Toast.LENGTH_SHORT).show();
                                        return false;
                                    }
                                }),
                        new SecondaryDrawerItem().withName(R.string.other_on_drawer),
                        new PrimaryDrawerItem()
                                .withName(R.string.login_out_on_drawer)
                                .withIcon(R.drawable.ic_error_outline_black_48dp)
                                .withIdentifier(6)
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                        return false;
                                    }
                                })
                ).build();
        drawer.openDrawer();
    }

    /**
     * 初始化语音识别
     */
    public void initSpeech(final Context context) {

//        Toast.makeText(MainActivity.this,"initSpeech",Toast.LENGTH_SHORT).show();

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission.RECORD_AUDIO},1);
        }else {
            //1.创建RecognizerDialog对象
            RecognizerDialog mDialog = new RecognizerDialog(context, null);
            //2.设置accent、language等参数
            mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
            //3.设置回调接口
            mDialog.setListener(new RecognizerDialogListener() {
                @Override
                public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                    if (!isLast) {
                        //解析语音
                        String result = parseVoice(recognizerResult.getResultString());
                        Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(SpeechError speechError) {

                }

            });
            //4.显示dialog，接收语音输入
            mDialog.show();
        }

    }

    /**
     * 解析语音json
     */
    public String parseVoice(String resultString) {
        Gson gson = new Gson();
        Voice voiceBean = gson.fromJson(resultString, Voice.class);

        StringBuffer sb = new StringBuffer();
        ArrayList<Voice.WSBean> ws = voiceBean.ws;
        for (Voice.WSBean wsBean : ws) {
            String word = wsBean.cw.get(0).w;
            sb.append(word);
        }
        return sb.toString();
    }

    /**
     * 语音对象封装
     */
    public class Voice {

        public ArrayList<WSBean> ws;

        public class WSBean {
            public ArrayList<CWBean> cw;
        }

        public class CWBean {
            public String w;
        }
    }

    private boolean changeSearchCard(int j){
        if (j > 0){
            if (i < 1) {
                i++;
                setImage(i);
                this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.animation_right_in));
                this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.animation_left_out));
                this.flipper.showNext();
            }
            return true;
        }else {
            if (i > 0) {
                i--;
                setImage(i);
                this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.animation_left_in));
                this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.animation_right_out));
                this.flipper.showPrevious();
            }
            return true;
        }
    }

    private void setupWindowAnimations(){
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);

        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
    }

    private void setImage(int i) {
        for(int j=0;j<2;j++)
        {
            if(j!=i)
                iamges[j].setImageResource(R.drawable.ic_lens_black_24dp_light);
            else
                iamges[j].setImageResource(R.drawable.ic_lens_black_24dp_dark);
        }
    }

    private void changeProfileImage(){

        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE },2);
        }else if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE }, 3);
        }else{
            Matisse.from(MainActivity.this)
                    .choose(MimeType.allOf())//图片类型
                    .countable(true)//true:选中后显示数字;false:选中后显示对号
                    .maxSelectable(1)//可选的最大数
                    .capture(false)//选择照片时，是否显示拍照
//                    .captureStrategy(new CaptureStrategy(true, "com.example.xx.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                    .imageEngine(new GlideEngine())//图片加载引擎
                    .forResult(REQUEST_CODE_CHOOSE);//
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> result = Matisse.obtainResult(data);
//            textView.setText(result.toString());
            Toast.makeText(MainActivity.this,result.get(0).toString(),Toast.LENGTH_SHORT).show();
            try {
                drawer.closeDrawer();
                initDrawer(SomeUtil.getBitmap(getContentResolver(),result.get(0)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return this.detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() > 120) {
            changeSearchCard(1);
        }
        else if (e1.getX() - e2.getX() < -120) {
            changeSearchCard(-1);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_btn_on_main:
                Intent intent = new Intent(MainActivity.this,AnswerListsActivity.class);
                startActivity(intent);
//                initSpeech(MainActivity.this);
                break;
            case R.id.image_key_on_main:
                if (i == 1){
                    changeSearchCard(-1);
                }
                break;
            case R.id.image_description_on_main:
                if (i == 0){
                    changeSearchCard(1);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initSpeech(MainActivity.this);
                }else {
                    Toast.makeText(MainActivity.this,"请开放录音权限",Toast.LENGTH_SHORT);
                }
                break;
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    changeProfileImage();
                }else {
                    Toast.makeText(MainActivity.this,"读内存权限",Toast.LENGTH_SHORT);
                }
                break;
            case 3:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    changeProfileImage();
                }else {
                    Toast.makeText(MainActivity.this,"写内存权限",Toast.LENGTH_SHORT);
                }
                break;
        }
    }
}