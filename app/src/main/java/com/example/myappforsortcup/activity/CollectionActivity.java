package com.example.myappforsortcup.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.example.myappforsortcup.R;
import com.example.myappforsortcup.adapter.AdapterBrief;
import com.example.myappforsortcup.bean.AnswerBrief;
import com.example.myappforsortcup.bean.CNCBriefBean;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class CollectionActivity extends AppCompatActivity implements View.OnClickListener {

    private List<CNCBriefBean> briefList = new ArrayList<CNCBriefBean>();
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AdapterBrief adapterBrief;

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        initData();
        initView();
    }

    private void initData(){
//        for (int i = 0;i < 20; i++){
//            AnswerBrief answerBrief = new AnswerBrief(i,"标题"+i, SomeUtil.RandomDoubleString("概述"+i,7),"网站源"+i,new Date(1000l));
//            briefList.add(answerBrief);
//        }

//        AnswerBrief answerBrief = new AnswerBrief(0,"控制系统主板的故障维修","一台工业控制机作为主控制、采用西班牙 $%&’( 系统作为数控部分的\n" +
//                "仿形键铣床，一次在加工完某一零件更换新的加工程序时， 突然出现死机现象且无任何\n" +
//                "报警，强行关机后重新起动系统，此时主机无法起动，同时出现显示器黑屏现象。","实用维修技术500例",new Date(2500l));
//        briefList.add(answerBrief);
//
//        answerBrief = new AnswerBrief(1,"表面磨损","机床切削半径为300mm的圆弧时，圆弧表面粗糙度很高，有明显的刀痕。 ","实用维修技术500例",new Date(2500l));
//        briefList.add(answerBrief);
//
//        answerBrief = new AnswerBrief(2,"声音异常","机床一开机就发出刺耳的噪声，\n" +
//                "并伴随着震动。","实用维修技术500例",new Date(2500l));
//        briefList.add(answerBrief);
//
//        answerBrief = new AnswerBrief(3,"主轴转速无显示。","主轴转速无显示。\n","实用维修技术500例",new Date(2500l));
//        briefList.add(answerBrief);
    }

    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_on_collection);
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

        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab_on_collection);
        floatingActionButton.setOnClickListener(this);

        layoutManager = new LinearLayoutManager(this);
        adapterBrief = new AdapterBrief(briefList);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_on_collection);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_on_collection:
                recyclerView.smoothScrollToPosition(0);
                break;
        }
    }
}
