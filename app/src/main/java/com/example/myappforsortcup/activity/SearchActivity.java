package com.example.myappforsortcup.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.myappforsortcup.R;
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

public class SearchActivity extends AppCompatActivity {

    Drawer drawer = null;
    IProfile profile = null;
    AccountHeader header = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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
                .withSavedInstance(savedInstanceState)
                .build();
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(header)
                .withToolbar((Toolbar)findViewById(R.id.toolbar_search))
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
                                .withOnCheckedChangeListener(checkedChangeListener),
                        new SwitchDrawerItem()
                                .withName(R.string.change_theme_color_on_drawer)
                                .withIcon(R.drawable.ic_invert_colors_black_48dp)
                                .withIdentifier(4)
                                .withOnCheckedChangeListener(checkedChangeListener)
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



    private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if(drawerItem instanceof Nameable) {
                Toast.makeText(SearchActivity.this,((Nameable)drawerItem).getName() + "'s check is" + isChecked,Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}
