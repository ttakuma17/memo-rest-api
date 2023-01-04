package com.rtjavamemoapp.application.controller;

import com.rtjavamemoapp.domain.model.Memo;

public class MemoResponse {

    private int id;
    private String title;
    private String description;
    private String category;
    private String date;
    private int markDiv;

    public MemoResponse(Memo memo) {
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.description = memo.getDescription();
        this.category = memo.getCategory();
        this.date = memo.getDate();
        this.markDiv = memo.getMarkDiv();
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public int getMarkDiv() {
        return markDiv;
    }

    public void setMarkDiv() {
        this.markDiv = markDiv;
    }
}
