package com.example.myappforsortcup.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myappforsortcup.R;
import com.example.myappforsortcup.util.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.image_view_on_main_2) ImageView imageViewMain2;

    @BindView(R.id.edit_text_on_main_2) EditText editTextMain2;

    @BindView(R.id.button_on_main_2) Button buttonMain2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        imageViewMain2.setImageBitmap(CodeUtils.getInstance().createBitmap());
    }

    @OnClick(R.id.image_view_on_main_2)
    void changeCodeImage(){
        imageViewMain2.setImageBitmap(CodeUtils.getInstance().createBitmap());
    }

    @OnClick(R.id.button_on_main_2)
    void checkCode(){
        String codeString = CodeUtils.getInstance().getCode();
        String editString = editTextMain2.getText().toString();

        if (codeString.equals(editString)){
            Toast.makeText(Main2Activity.this,"验证码正确",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(Main2Activity.this,codeString+"\n"+editString,Toast.LENGTH_SHORT).show();
        }
    }
}
