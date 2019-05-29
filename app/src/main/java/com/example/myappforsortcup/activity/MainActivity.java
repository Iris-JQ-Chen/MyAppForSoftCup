package com.example.myappforsortcup.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements OnGestureListener,View.OnClickListener {
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

    }


    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_drawer_white_24dp));

        iamges[0]=(ImageView) findViewById(R.id.image_key_on_main);
        iamges[1]=(ImageView) findViewById(R.id.image_description_on_main);
        iamges[0].setOnClickListener(this);
        iamges[1].setOnClickListener(this);

        detector = new GestureDetector(this);
        flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper1);
        flipper.addView(LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_by_key,null,false));
        flipper.addView(LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_by_description,null,false));

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
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
                            Toast.makeText(MainActivity.this,"点击头像",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    })
                    .withIdentifier(100);
        }else {
            profile = new ProfileDrawerItem()
                    .withName(getResources().getString(R.string.username))
                    .withEmail(getResources().getString(R.string.useremail))
                    .withIcon("http://noavatar.csdn.net/5/D/8/1_soma5431.jpg")
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
                                Toast.makeText(MainActivity.this,"the icon is clicked",Toast.LENGTH_SHORT).show();
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
                                .withChecked(true)  //设置默认为ON状态
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

    void setImage(int i) {
        for(int j=0;j<2;j++)
        {
            if(j!=i)
                iamges[j].setImageResource(R.drawable.ic_lens_black_24dp_light);
            else
                iamges[j].setImageResource(R.drawable.ic_lens_black_24dp_dark);
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
//            if (i < 1) {
//                i++;
//                setImage(i);
//                this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
//                        R.anim.animation_right_in));
//                this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
//                        R.anim.animation_left_out));
//                this.flipper.showNext();
//            }
//            return true;
            changeSearchCard(1);
        }
        else if (e1.getX() - e2.getX() < -120) {
//            if (i > 0) {
//                i--;
//                setImage(i);
//                this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
//                        R.anim.animation_left_in));
//                this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
//                        R.anim.animation_right_out));
//                this.flipper.showPrevious();
//            }
//            return true;
            changeSearchCard(-1);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
}