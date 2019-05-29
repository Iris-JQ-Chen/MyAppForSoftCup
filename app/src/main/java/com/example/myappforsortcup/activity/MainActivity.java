package com.example.myappforsortcup.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.myappforsortcup.R;

public class MainActivity extends Activity implements OnGestureListener {
    private GestureDetector detector;
    private ViewFlipper flipper;
    private Button button;
    ImageView []iamges=new ImageView[2];
    int i = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iamges[0]=(ImageView) findViewById(R.id.image_key_on_main);
        iamges[1]=(ImageView) findViewById(R.id.image_description_on_main);

        detector = new GestureDetector(this);
        flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper1);
        flipper.addView(LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_by_description,null,false));
        flipper.addView(LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_by_key,null,false));
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
        }
        else if (e1.getX() - e2.getX() < -120) {
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
        return false;

    }

}