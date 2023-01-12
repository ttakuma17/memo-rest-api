package com.rtjavamemoapp.domain.model;

import lombok.Data;

@Data
public class Memo {

    private int id;
    private String title;
    private String description;
    private String category;
    private String date;
    private int mark_div;

    public Memo(int id, String title, String description, String category, String date, int mark_div) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.date = date;
        this.mark_div = mark_div;
    }
    
}
