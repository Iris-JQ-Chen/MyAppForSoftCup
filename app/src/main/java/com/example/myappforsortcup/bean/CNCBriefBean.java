package com.example.myappforsortcup.bean;

public class CNCBriefBean {

    private int cnc_id;
    private String cnc_des;
    private String cnc_diagnose;
    private String cnc_source;
    private int cnc_like;
    private int cnc_view;

    public void setCncId(int cnc_id) {
        this.cnc_id = cnc_id;
    }

    public int getCncId() {
        return cnc_id;
    }

    public void setCncDes(String cnc_des) {
        this.cnc_des = cnc_des;
    }

    public String getCncDes() {
        return cnc_des;
    }

    public void setCncDiagnose(String cnc_diagnose) {
        this.cnc_diagnose = cnc_diagnose;
    }

    public String getCncDiagnose() {
        return cnc_diagnose;
    }

    public void setCncSource(String cnc_source) {
        this.cnc_source = cnc_source;
    }

    public String getCncSource() {
        return cnc_source;
    }

    public void setCncLike(int cnc_like) {
        this.cnc_like = cnc_like;
    }

    public int getCncLike() {
        return cnc_like;
    }

    public void setCncView(int cnc_view) {
        this.cnc_view = cnc_view;
    }

    public int getCncView() {
        return cnc_view;
    }

}
