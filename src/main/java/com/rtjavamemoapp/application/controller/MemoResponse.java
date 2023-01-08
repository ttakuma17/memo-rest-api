package com.rtjavamemoapp.application.controller;

import com.rtjavamemoapp.domain.model.Memo;
import lombok.Data;

@Data
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
    
}
