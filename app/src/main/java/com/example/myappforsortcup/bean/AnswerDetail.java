package com.example.myappforsortcup.bean;

/**
 * Created by 蒲公英之流 on 2019-05-21.
 */

public class AnswerDetail {

    private int id;
    private String brand;
    private String model;
    private String code;
    private String location;
    private String threshold;
    private String reality;
    private String question;
    private String diagnosis;
    private String removal;

    public AnswerDetail(int id,String brand,String model,String code, String location,String threshold,String reality,String question,String diagnosis, String removal){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.code = code;
        this.location = location;
        this.threshold = threshold;
        this.reality = reality;
        this.question = question;
        this.diagnosis = diagnosis;
        this.removal = removal;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getThreshold() {
        return threshold;
    }
    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }
    public String getReality() {
        return reality;
    }
    public void setReality(String reality) {
        this.reality = reality;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public String getRemoval() {
        return removal;
    }
    public void setRemoval(String removal) {
        this.removal = removal;
    }
}
