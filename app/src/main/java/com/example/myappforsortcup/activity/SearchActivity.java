package com.example.myappforsortcup.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

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

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    private Drawer drawer = null;
    private IProfile profile = null;
    private AccountHeader header = null;
    private Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        initDrawer();
        setupWindowAnimations();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (answerBriefList.isEmpty() || answerBriefList == null){
//
//        }else {
//            answerBriefList.clear();
//            adapterOnSearch.notifyDataSetChanged();
//        }
//    }

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
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.search_on_search:
//                initAnswerBriefLists();
//                adapterOnSearch.notifyDataSetChanged();
//                break;
//            case R.id.voice_on_search:

//                break;
        }
    }

    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_search);
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_drawer_white_24dp));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

//        searchImage = (ImageView)findViewById(R.id.search_on_search);
//        voiceImage = (ImageView)findViewById(R.id.voice_on_search);
//
//        searchImage.setOnClickListener(this);
//        voiceImage.setOnClickListener(this);
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
                            Toast.makeText(SearchActivity.this,"点击头像",Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(SearchActivity.this,"the icon is clicked",Toast.LENGTH_SHORT).show();
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
                                        Intent intent = new Intent(SearchActivity.this,CollectionActivity.class);
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
                                        Intent intent = new Intent(SearchActivity.this,HistoryActivity.class);
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
                                        Intent intent = new Intent(SearchActivity.this,ChangeInfoActivity.class);
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
                                        Toast.makeText(SearchActivity.this,"3"+isChecked,Toast.LENGTH_SHORT).show();
                                    }
                                }),
                        new SwitchDrawerItem()
                                .withName(R.string.change_theme_color_on_drawer)
                                .withIcon(R.drawable.ic_invert_colors_black_48dp)
                                .withIdentifier(5)
                                .withOnCheckedChangeListener(new OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
                                        Toast.makeText(SearchActivity.this,"4"+isChecked,Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .withChecked(true)  //设置默认为ON状态
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        //监听方法实现
                                        Toast.makeText(SearchActivity.this,"The DrawerItem is clicked",Toast.LENGTH_SHORT).show();
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
                                    Intent intent = new Intent(SearchActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                    return false;
                                }
                            })
                ).build();
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
