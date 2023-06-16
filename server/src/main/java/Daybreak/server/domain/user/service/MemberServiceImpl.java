package Daybreak.server.domain.user.service;

import Daybreak.server.domain.user.dto.MemberDto;
import Daybreak.server.domain.user.entity.Member;
import Daybreak.server.domain.user.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDto.Response createMember(MemberDto.Post post) {


        Member member = Member.builder()
                              .id(post.getId())
                              .password(post.getPassword())
                              .nickName(post.getNickName())
                              .createdAt(LocalDateTime.now())
                              .build();

        Member savedMember =  memberRepository.save(member);

        MemberDto.Response response = MemberDto.Response.builder()
                                                        .id(savedMember.getId())
                                                        .password(savedMember.getPassword())
                                                        .nickName(savedMember.getNickName())
                                                        .createdAt(LocalDateTime.now())
                                                        .build();

        return response;
    }
}
