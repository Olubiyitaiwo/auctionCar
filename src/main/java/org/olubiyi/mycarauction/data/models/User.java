package org.olubiyi.mycarauction.data.models;

import lombok.Data;

import java.util.UUID;


@Data
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
}
