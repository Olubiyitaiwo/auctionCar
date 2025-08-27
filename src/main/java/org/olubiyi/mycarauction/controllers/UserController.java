package org.olubiyi.mycarauction.controllers;

import lombok.AllArgsConstructor;
import org.olubiyi.mycarauction.data.models.User;
import org.olubiyi.mycarauction.dtos.request.UserRequestDto;
import org.olubiyi.mycarauction.dtos.response.UserResponseDto;
import org.olubiyi.mycarauction.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api/User")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.createUser(userRequestDto));
    }
}
