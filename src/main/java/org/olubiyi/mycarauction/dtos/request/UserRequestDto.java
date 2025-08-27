package org.olubiyi.mycarauction.dtos.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private String phone;
}

