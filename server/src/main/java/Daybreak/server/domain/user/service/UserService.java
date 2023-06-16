package Daybreak.server.domain.user.service;

import Daybreak.server.domain.user.dto.UserDto;

public interface UserService {

    UserDto.Response createUser(UserDto.Post post);
}
