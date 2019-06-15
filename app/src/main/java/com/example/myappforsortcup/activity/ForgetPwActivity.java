package com.example.myappforsortcup.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myappforsortcup.R;
import com.mob.MobSDK;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class ForgetPwActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etPhoneNumber;        // 电话号码
    private Button sendVerificationCode;   // 发送验证码
    private EditText etVerificationCode;   // 验证码
    private Button nextStep;               // 下一步

//    @BindView(R.id.edit_phone_number_on_fpw) EditText etPhoneNumber;
//    @BindView(R.id.btn_send_verification_code_on_fpw) Button sendVerificationCode;
//    @BindView(R.id.edit_verification_code_on_fpw) EditText etVerificationCode;
//    @BindView(R.id.btn_next_step_on_fpw) Button nextStep;


    private String phoneNumber;         // 电话号码
    private String verificationCode;    // 验证码

    private boolean flag;   // 操作是否成功

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pw);

        init(); // 初始化控件、注册点击事件

        final Context context = ForgetPwActivity.this;                       // context
        final String AppKey = "你的 AppKey";                       // AppKey
        final String AppSecret = "你的 AppSecret"; // AppSecret

//        SMSSDK.initSDK(context, AppKey, AppSecret);           // 初始化 SDK 单例，可以多次调用
        MobSDK.init(this);
        SMSSDK.setAskPermisionOnReadContact(true);
        EventHandler eventHandler = new EventHandler(){       // 操作回调
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eventHandler);     // 注册回调接口
    }

    private void init() {
        etPhoneNumber = (EditText) findViewById(R.id.edit_phone_number_on_fpw);
        sendVerificationCode = (Button) findViewById(R.id.btn_send_verification_code_on_fpw);
        etVerificationCode = (EditText) findViewById(R.id.edit_verification_code_on_fpw);
        nextStep = (Button) findViewById(R.id.btn_next_step_on_fpw);
        sendVerificationCode.setOnClickListener(this);
        nextStep.setOnClickListener(this);
    }
//
//    @OnClick(R.id.btn_send_verification_code_on_fpw)
//    void setSendVerificationCode(){
//        if (!TextUtils.isEmpty(etPhoneNumber.getText())) {
//            if (etPhoneNumber.getText().length() == 11) {
//                phoneNumber = etPhoneNumber.getText().toString();
//                SMSSDK.getVerificationCode("86", phoneNumber); // 发送验证码给号码的 phoneNumber 的手机
//                etVerificationCode.requestFocus();
//            }
//            else {
//                Toast.makeText(this, "请输入完整的电话号码", Toast.LENGTH_SHORT).show();
//                etPhoneNumber.requestFocus();
//            }
//        } else {
//            Toast.makeText(this, "请输入电话号码", Toast.LENGTH_SHORT).show();
//            etPhoneNumber.requestFocus();
//        }
//    }
//
//    @OnClick(R.id.btn_next_step_on_fpw)
//    void setNextStep(){
//        if (!TextUtils.isEmpty(etVerificationCode.getText())) {
//            if (etVerificationCode.getText().length() == 4) {
//                verificationCode = etVerificationCode.getText().toString();
//                SMSSDK.submitVerificationCode("86", phoneNumber, verificationCode);
//                flag = false;
//            } else {
//                Toast.makeText(this, "请输入完整的验证码", Toast.LENGTH_SHORT).show();
//                etVerificationCode.requestFocus();
//            }
//        } else {
//            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
//            etVerificationCode.requestFocus();
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_verification_code_on_fpw:
                if (!TextUtils.isEmpty(etPhoneNumber.getText())) {
                    if (etPhoneNumber.getText().length() == 11) {
                        phoneNumber = etPhoneNumber.getText().toString();
                        SMSSDK.getVerificationCode("86", phoneNumber); // 发送验证码给号码的 phoneNumber 的手机
                        etVerificationCode.requestFocus();
                    }
                    else {
                        Toast.makeText(this, "请输入完整的电话号码", Toast.LENGTH_SHORT).show();
                        etPhoneNumber.requestFocus();
                    }
                } else {
                    Toast.makeText(this, "请输入电话号码", Toast.LENGTH_SHORT).show();
                    etPhoneNumber.requestFocus();
                }
                break;

            case R.id.btn_next_step_on_fpw:
                if (!TextUtils.isEmpty(etVerificationCode.getText())) {
                    if (etVerificationCode.getText().length() == 4) {
                        verificationCode = etVerificationCode.getText().toString();
                        SMSSDK.submitVerificationCode("86", phoneNumber, verificationCode);
                        flag = false;
                    } else {
                        Toast.makeText(this, "请输入完整的验证码", Toast.LENGTH_SHORT).show();
                        etVerificationCode.requestFocus();
                    }
                } else {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    etVerificationCode.requestFocus();
                }
                break;

            default:
                break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;

            if (result == SMSSDK.RESULT_COMPLETE) {
                // 如果操作成功
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    // 校验验证码，返回校验的手机和国家代码
                    Toast.makeText(ForgetPwActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgetPwActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    // 获取验证码成功，true为智能验证，false为普通下发短信
                    Toast.makeText(ForgetPwActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    // 返回支持发送验证码的国家列表
                }
            } else {
                // 如果操作失败
                if (flag) {
                    Toast.makeText(ForgetPwActivity.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
                    etPhoneNumber.requestFocus();
                } else {
                    ((Throwable) data).printStackTrace();
                    Toast.makeText(ForgetPwActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();  // 注销回调接口
    }
}
