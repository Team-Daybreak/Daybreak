package Daybreak.server.domain.member.service;

import Daybreak.server.domain.member.dto.MemberDto;
import Daybreak.server.domain.member.entity.Member;
import Daybreak.server.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDto.Response createMember(MemberDto.Post post) {

        Member member = new Member(1L, post.getLoginId(), post.getPassword(), post.getNickName(),LocalDateTime.now());

        Member member1 = Member.builder()
                              .loginId(post.getLoginId())
                              .password(post.getPassword())
                              .nickName(post.getNickName())
                              .createdAt(LocalDateTime.now())
                              .build();

        Member savedMember = memberRepository.save(member);

        MemberDto.Response response = MemberDto.Response.builder()
                                                        .loginId(savedMember.getLoginId())
                                                        .password(savedMember.getPassword())
                                                        .nickName(savedMember.getNickName())
                                                        .createdAt(savedMember.getCreatedAt())
                                                        .build();

        return response;
    }

    public MemberDto.Response updateMember(Long memberId, MemberDto.Patch patch) {

        Optional<Member> member = memberRepository.findById(memberId);


        Member updateMember = Member.builder()
                .memberId(memberId)
                .loginId(member.get().getLoginId())
                .password(patch.getPassword())
                .nickName(patch.getNickName())
                .createdAt(member.get().getCreatedAt())
                .build();

        Member updatedMember = memberRepository.save(updateMember);

        MemberDto.Response response = MemberDto.Response.builder()
                .loginId(updatedMember.getLoginId())
                .password(updatedMember.getPassword())
                .nickName(updatedMember.getNickName())
                .createdAt(updatedMember.getCreatedAt())
                .build();

        return response;
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
