package com.example.myappforsortcup.dao;

import com.example.myappforsortcup.util.DBUtill;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 蒲公英之流 on 2019-06-17.
 */

public class UserDao {

    public static boolean Register(String user_name,String user_pwd){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_name",user_name)
                .add("user_pwd",user_pwd)
                .build();
        Request request = new Request.Builder()
                .url(DBUtill.url_register)
                .post(requestBody)
                .build();
        String responseData = new String("");
        try {
            Response response = client.newCall(request).execute();
            responseData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        if (responseData.equals("true")){
//            return true;
//        }else {
//            return false;
//        }
        return true;
    }

    public static boolean Register(String user_name,String user_pwd,String user_email){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_name",user_name)
                .add("user_pwd",user_pwd)
                .add("user_email",user_email)
                .build();
        Request request = new Request.Builder()
                .url(DBUtill.url_register)
                .post(requestBody)
                .build();
        String responseData = new String("");
        try {
            Response response = client.newCall(request).execute();
            responseData = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
//        if (responseData.equals("true")){
//            return true;
//        }else {
//            return false;
//        }
        return true;
    }

    public static boolean Login(String user_name,String user_pwd){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_name",user_name)
                .add("user_pwd",user_pwd)
                .build();
        Request request = new Request.Builder()
                .url(DBUtill.url_login)
                .post(requestBody)
                .build();
        String responseData = new String("");

        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (responseData.equals("true")){
            return true;
        }else {
            return false;
        }

    }

    public static void ChangeUserInfo(String user_id,String info_type, String info_content){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_id",user_id)
                .add(info_type,info_content)
                .build();
        Request request = new Request.Builder()
                .url(DBUtill.url_user_info_add)
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
