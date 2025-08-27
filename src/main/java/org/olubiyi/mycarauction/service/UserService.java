package org.olubiyi.mycarauction.service;

import org.olubiyi.mycarauction.dtos.request.UserRequestDto;
import org.olubiyi.mycarauction.dtos.response.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto request);
}
