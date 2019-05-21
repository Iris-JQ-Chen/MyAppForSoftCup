package com.example.myappforsortcup.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappforsortcup.R;
import com.joaquimley.faboptions.FabOptions;

public class ShowAnswer extends AppCompatActivity {

    public static final String ANSWER_ID = "answer_id";
    public static final String QUESTION_TITLE = "question_title";
    public static final String ANSWER_BRIEF_DESCRIPTION = "answer_brief";
    public static final String ANSWER_DETAIL_CONTENT = "answer_detail";
    public static final String ANSWER_SOURCE_WEB = "answer_source_web";
    public static final String ANSWER_DATE = "answer_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answer);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_on_show_answer);
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


        Intent intent = getIntent();
        int id = intent.getIntExtra(ANSWER_ID,-1);
        if (id == -1){
            finish();
        }
        String title = intent.getStringExtra(QUESTION_TITLE);
        String briefDescription = intent.getStringExtra(ANSWER_BRIEF_DESCRIPTION);

        ((TextView)findViewById(R.id.question_title_on_show_answer)).setText(title);
        ((TextView)findViewById(R.id.detail_content_on_answer)).setText(briefDescription);

        FabOptions fabOptions = (FabOptions)findViewById(R.id.faboption_on_show_answer);
        fabOptions.setButtonsMenu(R.menu.menu_on_show_answer);
        fabOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.faboptions_like:
                        Toast.makeText(ShowAnswer.this,"点击点赞",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.faboptions_collect:
                        Toast.makeText(ShowAnswer.this,"点击收藏",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
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
}
