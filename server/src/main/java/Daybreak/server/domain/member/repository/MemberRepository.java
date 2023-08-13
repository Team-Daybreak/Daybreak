package Daybreak.server.domain.member.repository;

import Daybreak.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByNickName(String nickName);

    void  deleteById(Long memberId);

}
