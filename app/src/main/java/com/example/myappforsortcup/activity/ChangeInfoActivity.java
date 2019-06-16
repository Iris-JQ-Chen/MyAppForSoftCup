package com.example.myappforsortcup.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappforsortcup.R;
import com.example.myappforsortcup.fragment.EditDialogFragment;
import com.example.myappforsortcup.util.SomeUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.IOException;
import java.util.List;

public class ChangeInfoActivity extends AppCompatActivity implements View.OnClickListener, EditDialogFragment.NoticeDialogListener{

    private static final int REQUEST_CODE_CHOOSE = 20;

    private Toolbar toolbar;

    private TextView originalSignatureText;
    private TextView originalUsernameText;
    private TextView originalEmailText;
    private TextView originalTeleText;
    private TextView originalJobText;
    private TextView originalCompanyText;
    private TextView originalSchoolText;
    private TextView originalAddressText;

    private Bundle bundle;
    private Uri profileUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        initView();
    }

    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_on_change_info);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("profile_uri",profileUri);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        originalSignatureText = (TextView)findViewById(R.id.original_signature_on_change_info);
        originalUsernameText = (TextView)findViewById(R.id.original_username_on_change_info);
        originalEmailText = (TextView)findViewById(R.id.original_email_on_change_info);
        originalTeleText = (TextView)findViewById(R.id.original_tele_on_change_info);
        originalJobText = (TextView)findViewById(R.id.original_job_on_change_info);
        originalCompanyText = (TextView)findViewById(R.id.original_company_on_change_info);
        originalSchoolText = (TextView)findViewById(R.id.original_school_on_change_info);
        originalAddressText = (TextView)findViewById(R.id.original_address_on_change_info);

        findViewById(R.id.change_profile_on_change_info).setOnClickListener(this);
        findViewById(R.id.change_signature_on_change_info).setOnClickListener(this);
        findViewById(R.id.change_username_on_change_info).setOnClickListener(this);
        findViewById(R.id.change_email_on_change_info).setOnClickListener(this);
        findViewById(R.id.change_tele_on_change_info).setOnClickListener(this);
        findViewById(R.id.change_job_on_change_info).setOnClickListener(this);
        findViewById(R.id.change_company_on_change_info).setOnClickListener(this);
        findViewById(R.id.change_school_on_change_info).setOnClickListener(this);
        findViewById(R.id.change_address_on_change_info).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_profile_on_change_info:
                changeProfileImage();
                break;
            case R.id.change_signature_on_change_info:
                showEditSignautreDialog();
                break;
            case R.id.change_username_on_change_info:
                showEditUsernameDialog();
                break;
            case R.id.change_email_on_change_info:
                showEditEmailDialog();
                break;
            case R.id.change_tele_on_change_info:
                showEditTeleDialog();
                break;
            case R.id.change_job_on_change_info:
                showEditJobDialog();
                break;
            case R.id.change_company_on_change_info:
                showEditCompanyDialog();
                break;
            case R.id.change_school_on_change_info:
                showEditSchoolDialog();
                break;
            case R.id.change_address_on_change_info:
                showEditAddressDialog();
                break;
        }
    }

    private void changeProfileImage(){

        if (ContextCompat.checkSelfPermission(ChangeInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ChangeInfoActivity.this,new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE },2);
        }else if (ContextCompat.checkSelfPermission(ChangeInfoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ChangeInfoActivity.this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE }, 3);
        }else{
            Matisse.from(ChangeInfoActivity.this)
                    .choose(MimeType.allOf())//图片类型
                    .countable(true)//true:选中后显示数字;false:选中后显示对号
                    .maxSelectable(1)//可选的最大数
                    .capture(false)//选择照片时，是否显示拍照
//                    .captureStrategy(new CaptureStrategy(true, "com.example.xx.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                    .imageEngine(new GlideEngine())//图片加载引擎
                    .forResult(REQUEST_CODE_CHOOSE);//
        }
    }

    private void showEditSignautreDialog(){
        EditDialogFragment dialogFragment = new EditDialogFragment();
        bundle = new Bundle();
        bundle.putString("message","我的签名");
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(),"EditNameDialog");
    }

    private void showEditUsernameDialog(){
        EditDialogFragment dialogFragment = new EditDialogFragment();
        bundle = new Bundle();
        bundle.putString("message","我的昵称");
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(),"EditNameDialog");
    }

    private void showEditEmailDialog(){
        EditDialogFragment dialogFragment = new EditDialogFragment();
        bundle = new Bundle();
        bundle.putString("message","我的邮箱");
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(),"EditNameDialog");
    }

    private void showEditTeleDialog(){
        EditDialogFragment dialogFragment = new EditDialogFragment();
        bundle = new Bundle();
        bundle.putString("message","我的电话");
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(),"EditNameDialog");
    }

    private void showEditJobDialog(){
        EditDialogFragment dialogFragment = new EditDialogFragment();
        bundle = new Bundle();
        bundle.putString("message","我的职业");
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(),"EditNameDialog");
    }

    private void showEditCompanyDialog(){
        EditDialogFragment dialogFragment = new EditDialogFragment();
        bundle = new Bundle();
        bundle.putString("message","我的公司");
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(),"EditNameDialog");
    }

    private void showEditSchoolDialog(){
        EditDialogFragment dialogFragment = new EditDialogFragment();
        bundle = new Bundle();
        bundle.putString("message","我的学校");
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(),"EditNameDialog");
    }

    private void showEditAddressDialog(){
        EditDialogFragment dialogFragment = new EditDialogFragment();
        bundle = new Bundle();
        bundle.putString("message","我的所在地");
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(),"EditNameDialog");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("profile_uri",profileUri);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onDialogPositiveClick(EditDialogFragment dialog) {
        if(bundle != null){
            switch (bundle.getString("message")){
                case "我的签名":
                    originalSignatureText.setText(dialog.myData);
                    break;
                case "我的昵称":
                    originalUsernameText.setText(dialog.myData);
                    break;
                case "我的邮箱":
                    originalEmailText.setText(dialog.myData);
                    break;
                case "我的电话":
                    originalTeleText.setText(dialog.myData);
                    break;
                case "我的职业":
                    originalJobText.setText(dialog.myData);
                    break;
                case "我的公司":
                    originalCompanyText.setText(dialog.myData);
                    break;
                case "我的学校":
                    originalSchoolText.setText(dialog.myData);
                    break;
                case "我的所在地":
                    originalAddressText.setText(dialog.myData);
                    break;
            }
        }
    }

    @Override
    public void onDialogNegativeClick(EditDialogFragment dialog) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> result = Matisse.obtainResult(data);
//            textView.setText(result.toString());
            profileUri = result.get(0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    changeProfileImage();
                }else {
                    Toast.makeText(ChangeInfoActivity.this,"读内存权限",Toast.LENGTH_SHORT);
                }
                break;
            case 3:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    changeProfileImage();
                }else {
                    Toast.makeText(ChangeInfoActivity.this,"写内存权限",Toast.LENGTH_SHORT);
                }
                break;
        }
    }
}
