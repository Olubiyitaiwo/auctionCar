package org.olubiyi.mycarauction.service;

import lombok.AllArgsConstructor;
import org.olubiyi.mycarauction.data.models.User;
import org.olubiyi.mycarauction.data.repositories.BidRepository;
import org.olubiyi.mycarauction.data.repositories.UserRepository;
import org.olubiyi.mycarauction.dtos.request.UserRequestDto;
import org.olubiyi.mycarauction.dtos.response.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BidRepository bidRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto request) {
        User user = new User();

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        userRepository.save(user);

        return new UserResponseDto(user.getId(),user.getEmail(),
                user.getPassword(),user.getFirstName(),
                user.getLastName());

    }

}
