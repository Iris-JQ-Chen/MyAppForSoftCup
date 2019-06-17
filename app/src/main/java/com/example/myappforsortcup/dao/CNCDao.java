package com.example.myappforsortcup.dao;

import com.example.myappforsortcup.util.DBUtill;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 蒲公英之流 on 2019-06-18.
 */

public class CNCDao {

    public static String SearchBrief(String des){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("cnc_des",des)
                .build();
        Request request = new Request.Builder()
                .url(DBUtill.url_search_brief)
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

        return responseData;
    }

    public static String SearchRetail(String id){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("cnc_id", id)
                .build();
        Request request = new Request.Builder()
                .url(DBUtill.url_search_retail)
                .post(requestBody)
                .build();
        String responseData = new String("");
        try {
            Response response = client.newCall(request).execute();
            responseData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseData;
    }

}
