package org.olubiyi.mycarauction.service;

import lombok.AllArgsConstructor;
import org.olubiyi.mycarauction.data.models.User;
import org.olubiyi.mycarauction.data.repositories.UserRepository;
import org.olubiyi.mycarauction.dtos.request.UserRequestDto;
import org.olubiyi.mycarauction.dtos.response.UserResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // for hashing passwords

    @Override
    public UserResponseDto createUser(UserRequestDto request) {
        User user = new User();

        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // hash password
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());

        User savedUser = userRepository.save(user);

        // Return safe response (no password!)
        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getUsername(),
                savedUser.getPhone()
        );
    }
}
