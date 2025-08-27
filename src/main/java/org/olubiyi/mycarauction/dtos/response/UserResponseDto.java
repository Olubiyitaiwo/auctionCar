package org.olubiyi.mycarauction.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {
    private Object id;  // can be UUID or String depending on your need
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String phone;
}
