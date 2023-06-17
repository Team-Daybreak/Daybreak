package Daybreak.server.domain.user.service;

import Daybreak.server.domain.user.dto.MemberDto;

public interface MemberService {

    MemberDto.Response createMember(MemberDto.Post post);

    MemberDto.Response updateMember(Long memberId, MemberDto.Patch patch);
}
