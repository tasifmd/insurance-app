package com.tasif.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank(message = "User name can't be blank")
    private  String name;

    @Email(message = "Invalid email")
    private String email;

    @Size(min = 6, message = "Password should be at least 6 character")
    private String password;
}
