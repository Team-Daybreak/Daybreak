package Daybreak.server.domain.user.repository;

import Daybreak.server.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(Long id);
    Optional<Member> findById(String id);

    Optional<Member> findByNickName(String nickName);
}
