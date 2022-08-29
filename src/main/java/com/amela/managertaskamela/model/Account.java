package com.amela.managertaskamela.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
    private int id;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Ten dang nhap chua hop le, moi nhap lai...")
    private String username;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9@_!]+$", message = "Mat khau khong hop le, moi nhap lai...")
    private String password;

    @NotBlank
    private String re_password;
}
