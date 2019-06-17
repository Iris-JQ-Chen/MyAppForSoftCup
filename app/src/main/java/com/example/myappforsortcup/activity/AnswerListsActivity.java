package com.example.myappforsortcup.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.daimajia.androidanimations.library.sliders.SlideInLeftAnimator;
import com.example.myappforsortcup.R;
import com.example.myappforsortcup.adapter.AdapterBrief;
import com.example.myappforsortcup.bean.AnswerBrief;
import com.example.myappforsortcup.bean.CNCBriefBean;
import com.example.myappforsortcup.util.SomeUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class AnswerListsActivity extends AppCompatActivity implements View.OnClickListener {

    private List<AnswerBrief> briefList = new ArrayList<AnswerBrief>();
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AdapterBrief adapterBrief;

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_lists);

        initData();
        initView();
        initListener();
    }

    private void initData(){
//        for (int i = 0;i < 20; i++){
//            AnswerBrief answerBrief = new AnswerBrief(i,"标题"+i, SomeUtil.RandomDoubleString("概述"+i,7),"网站源"+i,new Date(1000l));
//            briefList.add(answerBrief);
//        }
        AnswerBrief answerBrief = new AnswerBrief(0,"控制系统主板的故障维修","一台工业控制机作为主控制、采用西班牙 $%&’( 系统作为数控部分的\n" +
                "仿形键铣床，一次在加工完某一零件更换新的加工程序时， 突然出现死机现象且无任何\n" +
                "报警，强行关机后重新起动系统，此时主机无法起动，同时出现显示器黑屏现象。","实用维修技术500例",new Date(2500l));
        briefList.add(answerBrief);

        Intent intent = getIntent();
        String string = intent.getStringExtra("briefList");
        briefList = new Gson().fromJson(string,new TypeToken<List<CNCBriefBean>>() {}.getType());
    }

    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab_on_answer_list);
        floatingActionButton.setOnClickListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_on_answer_list);

        layoutManager = new LinearLayoutManager(this);
        adapterBrief = new AdapterBrief(briefList);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_on_answer_list);
        recyclerView.setLayoutManager(layoutManager);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapterBrief);
        alphaAdapter.setDuration(1000);
        alphaAdapter.setInterpolator(new OvershootInterpolator());
        recyclerView.setAdapter(alphaAdapter);
        SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));  //相比于第一种，第二种为SlideInUpAnimator添加插值器
        animator.setInterpolator(new OvershootInterpolator());
        recyclerView.setItemAnimator(animator);
        recyclerView.getItemAnimator().setAddDuration(1000);
        recyclerView.getItemAnimator().setRemoveDuration(1000);
        recyclerView.getItemAnimator().setMoveDuration(1000);
        recyclerView.getItemAnimator().setChangeDuration(1000);
    }

    private void initListener() {
        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        adapterBrief.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_on_answer_list:
                recyclerView.smoothScrollToPosition(0);
                break;
        }
    }
}
