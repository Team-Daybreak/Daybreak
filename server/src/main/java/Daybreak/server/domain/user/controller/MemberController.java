package Daybreak.server.domain.user.controller;


import Daybreak.server.domain.user.dto.MemberDto;
import Daybreak.server.domain.user.service.MemberService;
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
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity postUser(@Validated @RequestBody MemberDto.Post post) {
        MemberDto.Response response = memberService.createMember(post);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
