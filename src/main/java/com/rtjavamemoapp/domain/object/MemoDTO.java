package com.rtjavamemoapp.domain.object;

import lombok.Data;

@Data
public class MemoDTO {

  private int id;
  private String title;
  private String description;
  private String category;
  private String date;
  private int markDiv;
}
