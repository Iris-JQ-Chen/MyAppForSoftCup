package com.example.myappforsortcup.bean;

import java.sql.Date;

/**
 * Created by 蒲公英之流 on 2019-05-21.
 */

public class AnswerBrief {

    private int id;
    private String title;
    private String briefDescription;
    private String sourceWeb;
    private Date data;

    public AnswerBrief(int id, String title, String briefDescription, String sourceWeb, Date date){
        this.id = id;
        this.title = title;
        this.briefDescription = briefDescription;
        this.sourceWeb = sourceWeb;
        this.data = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public String getSourceWeb() {
        return sourceWeb;
    }

    public void setSourceWeb(String sourceWeb) {
        this.sourceWeb = sourceWeb;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
