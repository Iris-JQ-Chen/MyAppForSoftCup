package com.example.myappforsortcup.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.OvershootInterpolator;

import com.daimajia.androidanimations.library.sliders.SlideInLeftAnimator;
import com.example.myappforsortcup.R;
import com.example.myappforsortcup.adapter.AdapterBrief;
import com.example.myappforsortcup.bean.AnswerBrief;
import com.example.myappforsortcup.util.SomeUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class AnswerListsActivity extends AppCompatActivity {

    private List<AnswerBrief> briefList = new ArrayList<AnswerBrief>();
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AdapterBrief adapterBrief;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_lists);

        initData();
        initView();
    }

    private void initData(){
        for (int i = 0;i < 20; i++){
            AnswerBrief answerBrief = new AnswerBrief(i,"标题"+i, SomeUtil.RandomDoubleString("概述"+i,7),"网站源"+i,new Date(1000l));
            briefList.add(answerBrief);
        }
    }

    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_list);
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_arrow_back_white_24dp));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

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
}
