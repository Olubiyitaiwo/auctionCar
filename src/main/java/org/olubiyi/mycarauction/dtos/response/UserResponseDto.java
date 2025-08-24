package org.olubiyi.mycarauction.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String message;
}
