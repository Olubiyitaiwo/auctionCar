package org.olubiyi.mycarauction.dtos.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
}
