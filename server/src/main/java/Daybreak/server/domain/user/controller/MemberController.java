package Daybreak.server.domain.user.controller;


import Daybreak.server.domain.user.dto.MemberDto;
import Daybreak.server.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity postMember(@Validated @RequestBody MemberDto.Post post) {
        MemberDto.Response response = memberService.createMember(post);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity patchMember(@PathVariable("memberId") Long memberId,
                                      @Validated @RequestBody MemberDto.Patch patch) {
        MemberDto.Response response = memberService.updateMember(memberId, patch);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
