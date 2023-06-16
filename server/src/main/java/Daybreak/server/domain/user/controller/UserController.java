package Daybreak.server.domain.user.controller;


import Daybreak.server.domain.user.dto.UserDto;
import Daybreak.server.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity postUser(@Validated @RequestBody UserDto.Post post) {
        UserDto.Response response = userService.createUser(post);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
