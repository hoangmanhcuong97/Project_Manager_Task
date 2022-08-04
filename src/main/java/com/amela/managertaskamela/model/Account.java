package com.amela.managertaskamela.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private int id;

    private String username;

    private String password;

    private String re_password;
}
