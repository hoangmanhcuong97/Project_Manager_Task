package com.amela.managertaskamela.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private int id;
    @NotBlank(message = "Title can't blank!!")
    private String title;
    @NotBlank(message = "Content can't blank!!")
    private String content;

    private String status;

    private Account account;
}
