package Daybreak.server.domain.user.service;

import Daybreak.server.domain.user.dto.UserDto;
import Daybreak.server.domain.user.entity.User;
import Daybreak.server.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto.Response createUser(UserDto.Post post) {


        User user = User.builder()
                .id(post.getId())
                .password(post.getPassword())
                .nickName(post.getNickName())
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser =  userRepository.save(user);

        UserDto.Response response = UserDto.Response.builder()
                .id(savedUser.getId())
                .password(savedUser.getPassword())
                .nickName(savedUser.getNickName())
                .createdAt(LocalDateTime.now())
                .build();

        return response;
    }
}
