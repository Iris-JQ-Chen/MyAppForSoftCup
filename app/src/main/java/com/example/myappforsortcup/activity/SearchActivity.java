package com.example.myappforsortcup.activity;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myappforsortcup.R;
import com.example.myappforsortcup.adapter.AdapterOnSearch;
import com.example.myappforsortcup.bean.AnswerBrief;
import com.example.myappforsortcup.util.SomeUtil;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    private Drawer drawer = null;
    private IProfile profile = null;
    private AccountHeader header = null;
    private Toolbar toolbar = null;

    private List<AnswerBrief> answerBriefList = new ArrayList<AnswerBrief>();
    private RecyclerView recyclerViewOnSearch = null;
    private AdapterOnSearch  adapterOnSearch = null;
    private RecyclerView.LayoutManager layoutManager = null;

    private ImageView searchImage;
    private ImageView voiceImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        initDrawer();
        initRecyclerRelate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (answerBriefList.isEmpty() || answerBriefList == null){

        }else {
            answerBriefList.clear();
            adapterOnSearch.notifyDataSetChanged();
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_on_search:
                initAnswerBriefLists();
                adapterOnSearch.notifyDataSetChanged();
                break;
            case R.id.voice_on_search:

                break;
        }
    }

    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_search);
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_drawer_white_24dp));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        searchImage = (ImageView)findViewById(R.id.search_on_search);
        voiceImage = (ImageView)findViewById(R.id.voice_on_search);

        searchImage.setOnClickListener(this);
        voiceImage.setOnClickListener(this);
    }

    private void initDrawer(){
        profile = new ProfileDrawerItem()
                .withName(getResources().getString(R.string.username))
                .withEmail(getResources().getString(R.string.useremail))
                .withIcon("http://noavatar.csdn.net/5/D/8/1_soma5431.jpg")
                .withIdentifier(100);
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
//                .withSavedInstance(savedInstanceState)
                .build();
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(header)
//                .withToolbar((Toolbar)findViewById(R.id.toolbar_search))
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.history_on_drawer)
//                                .withDescription("This is a user")
                                .withIcon(R.drawable.ic_history_black_48dp)
                                .withIdentifier(1)
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Toast.makeText(SearchActivity.this,getTitle(),Toast.LENGTH_SHORT).show();
                                        return false;
                                    }
                                })
                                .withSelectable(false),
                        new SectionDrawerItem().withName(R.string.setting_on_drawer),  //分组item，类似于group标签，无点击效果
                        new SwitchDrawerItem()  //添加带有switch开关的item
                                .withName(R.string.change_language_on_drawer)
                                .withIcon(R.drawable.ic_language_black_48dp)
                                .withIdentifier(3)
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
                                .withIdentifier(4)
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
                                })
                ).build();
    }

    private void initAnswerBriefLists(){
        for (int i = 0;i < 50; i++){
            answerBriefList.add(new AnswerBrief(i+1,"问题标题"+i, SomeUtil.RandomDoubleString("回答简略内容"+i,70),"来源"+i,new Date(20L)));
        }
    }

    private void initRecyclerRelate(){
        recyclerViewOnSearch = (RecyclerView)findViewById(R.id.recycler_view_on_search);
        layoutManager = new LinearLayoutManager(this);
        adapterOnSearch = new AdapterOnSearch(answerBriefList);

        recyclerViewOnSearch.setLayoutManager(layoutManager);
        recyclerViewOnSearch.setAdapter(adapterOnSearch);
    }

}
