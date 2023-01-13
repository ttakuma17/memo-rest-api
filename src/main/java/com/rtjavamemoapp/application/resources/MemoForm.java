package com.rtjavamemoapp.application.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class MemoForm {

    private int id;
    @NotBlank
    @Size(min = 1, max = 20)
    private String title;
    private String description;
    private String category;
    @NotBlank
    @Pattern(regexp = "^[0-9]{4}\\/(0[1-9]|1[0-2])\\/(0[1-9]|[12][0-9]|3[01])$")
    private String date;
    @Range(min = 0, max = 1)
    private int mark_div;

}
