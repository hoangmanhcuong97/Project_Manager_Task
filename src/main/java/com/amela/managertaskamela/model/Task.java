package com.amela.managertaskamela.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.annotation.Generated;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    private int id;
    @NotBlank(message = "Title can't blank!!")
    private String title;
    @NotBlank(message = "Content can't blank!!")
    private String content;

    private String status;

    private Account account;
}
