package com.example.myappforsortcup.bean;

public class CNCRetailBean {

    private int cnc_id;
    private String cnc_des;
    private String cnc_diagnose;
    private String cnc_removal;
    private String cnc_brand;
    private String cnc_model;
    private String cnc_code;
    private String cnc_location;
    private String cnc_threshold;
    private String cnc_reality;
    private String cnc_source;
    private int cnc_like;
    private int cnc_view;

    public CNCRetailBean(){

    }

    public CNCRetailBean(int id,String des,String diagnose, String removal,
                         String brand,String model,String code,
                         String location,String threshold,String reality,
                         String source, int like, int view){
        cnc_id = id;
        cnc_des = des;
        cnc_diagnose = diagnose;
        cnc_removal = removal;
        cnc_brand = brand;
        cnc_model = model;
        cnc_code = code;
        cnc_location = location;
        cnc_threshold = threshold;
        cnc_source = source;
        cnc_like = like;
        cnc_view = view;
    }

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

    public void setCncRemoval(String cnc_removal) {
        this.cnc_removal = cnc_removal;
    }

    public String getCncRemoval() {
        return cnc_removal;
    }

    public void setCncBrand(String cnc_brand) {
        this.cnc_brand = cnc_brand;
    }

    public String getCncBrand() {
        return cnc_brand;
    }

    public void setCncModel(String cnc_model) {
        this.cnc_model = cnc_model;
    }

    public String getCncModel() {
        return cnc_model;
    }

    public void setCncCode(String cnc_code) {
        this.cnc_code = cnc_code;
    }

    public String getCncCode() {
        return cnc_code;
    }

    public void setCncLocation(String cnc_location) {
        this.cnc_location = cnc_location;
    }

    public String getCncLocation() {
        return cnc_location;
    }

    public void setCncThreshold(String cnc_threshold) {
        this.cnc_threshold = cnc_threshold;
    }

    public String getCncThreshold() {
        return cnc_threshold;
    }

    public void setCncReality(String cnc_reality) {
        this.cnc_reality = cnc_reality;
    }

    public String getCncReality() {
        return cnc_reality;
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
