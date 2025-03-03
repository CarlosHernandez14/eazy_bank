package com.charly.eazybank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
@AllArgsConstructor
public class RegisterUserDTO {

    private String email;
    private String password;
    // List of  roles that the user has
    private List<String> roles;


}