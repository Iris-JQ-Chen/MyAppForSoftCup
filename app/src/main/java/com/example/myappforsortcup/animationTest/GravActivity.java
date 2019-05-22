package com.example.myappforsortcup.animationTest;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.myappforsortcup.R;
import com.example.myappforsortcup.activity.SearchActivity;
import com.example.myappforsortcup.fragment.GravSampleFragment;

public class GravActivity extends AppCompatActivity {

    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grav);

        toolbar = (Toolbar)findViewById(R.id.toolbar_on_grav);
        setSupportActionBar(toolbar);

        showView(R.layout.grav);

        final Intent intent = new Intent(GravActivity.this,SearchActivity.class);
//        Timer timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                startActivity(intent);
//            }
//        };
//        timer.schedule(timerTask,1000 * 4);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        };
        new Handler().postDelayed(r, 1000 * 4);
    }


    private void showView(@LayoutRes int view){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, GravSampleFragment.newInstance(view))
                .commit();
    }

}
