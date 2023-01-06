package com.rtjavamemoapp.domain.model;

public class Memo {

    private int id;
    private String title;
    private String description;
    private String category;
    private String date;
    private int markDiv;

    public Memo(int id, String title, String description, String category, String date, int markDiv){
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.date = date;
        this.markDiv = markDiv;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getCategory(){
        return category;
    }

    public String getDate(){
        return date;
    }

    public int getMarkDiv() {
        return markDiv;
    }
}
