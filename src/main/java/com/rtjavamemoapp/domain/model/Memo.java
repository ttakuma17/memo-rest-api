package com.rtjavamemoapp.domain.model;

import lombok.Data;

@Data
public class Memo {

    private int id;
    private String title;
    private String category;
    private String description;
    private String date;
    private int markDiv;

    public Memo(int id, String title, String category, String description, String date,
        int markDiv) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.markDiv = markDiv;
    }
    
}
