package com.example.myappforsortcup.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.myappforsortcup.R;
import com.joaquimley.faboptions.FabOptions;

public class ShowAnswer extends AppCompatActivity implements GestureDetector.OnGestureListener {

    public static final String ANSWER_ID = "answer_id";
    public static final String QUESTION_TITLE = "question_title";
    public static final String ANSWER_BRIEF_DESCRIPTION = "answer_brief";
    public static final String ANSWER_DETAIL_CONTENT = "answer_detail";
    public static final String ANSWER_SOURCE_WEB = "answer_source_web";
    public static final String ANSWER_DATE = "answer_date";

    private Toolbar toolbar;
    private Intent intentFromSearch;
    private FabOptions fabOptions;
    private TextView diagnosisTextView;
    private TextView removalTextView;

    private String title;
    private String briefDescription;
    private String diagnosisFaultText;
    private String removalFaultText;

    private GestureDetector detector;
    private ViewFlipper flipper;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answer);

        title = getResources().getString(R.string.title);
        briefDescription = getResources().getString(R.string.briefDescription);
        diagnosisFaultText = getResources().getString(R.string.diagnoseFault);
        removalFaultText = getResources().getString(R.string.removeFault);
//        preIntent();
        initView();
    }

    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_on_show_answer);
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(ShowAnswer.this,"sdfjsld",Toast.LENGTH_SHORT).show();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        ((TextView)findViewById(R.id.question_title_on_show_answer)).setText(title);
        View viewRemoval = View.inflate(this,R.layout.activity_show_removal,null);
        removalTextView = (TextView)viewRemoval.findViewById(R.id.text_remove_fault);
        new Thread(new Runnable() {
            @Override
            public void run() {
                removalTextView.setText("爱丽丝分解落实到附近sldjfalsdkfjas");
            }
        }).start();


        fabOptions = (FabOptions)findViewById(R.id.faboption_on_show_answer);
        fabOptions.setButtonsMenu(R.menu.menu_on_show_answer);
        fabOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.faboptions_like:
                        Log.d("SoftCup","like"+removalTextView.getText().toString());
                        removalTextView.setText("了速度快放假酸辣粉加适量的飞机上来得快福建省");
                        Log.d("SoftCup","like"+removalTextView.getText().toString());
                        break;
                    case R.id.faboptions_un_like:
                        break;
                    case R.id.faboptions_thank:
                        break;
                    case R.id.faboptions_collect:
                        Toast.makeText(ShowAnswer.this,"点击收藏",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        detector = new GestureDetector(this);
        flipper = (ViewFlipper)findViewById(R.id.view_flipper_on_show);
        flipper.addView(LayoutInflater.from(ShowAnswer.this).inflate(R.layout.activity_show_diagnosis,null,false));
        flipper.addView(LayoutInflater.from(ShowAnswer.this).inflate(R.layout.activity_show_removal,null,false));
    }

    private void preIntent(){
        intentFromSearch = getIntent();
        int id = intentFromSearch.getIntExtra(ANSWER_ID,-1);
        if (id == -1){
            finish();
        }
        title = intentFromSearch.getStringExtra(QUESTION_TITLE);
        briefDescription = intentFromSearch.getStringExtra(ANSWER_BRIEF_DESCRIPTION);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Toast.makeText(ShowAnswer.this,"sldifjs",Toast.LENGTH_SHORT);
                break;
        }
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        Log.d("Touch","TouchEvent");
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
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("Touch","Fling");
        if (e1.getX() - e2.getX() > 100) {
            Log.d("Touch","100");
            if (page < 1) {
                Log.d("Touch",""+page);
                page++;
                this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.animation_right_in));
                this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.animation_left_out));
                this.flipper.showNext();
            }
            return true;
        }
        else if (e1.getX() - e2.getX() < -100) {
            Log.d("Touch","-100");
            if (page > 0) {
                Log.d("Touch",""+page);
                page--;
                this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.animation_left_in));
                this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.animation_right_out));
                this.flipper.showPrevious();
            }
            return true;
        }
        return false;
    }

}
