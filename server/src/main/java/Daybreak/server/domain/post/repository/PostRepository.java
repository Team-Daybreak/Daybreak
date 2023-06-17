package Daybreak.server.domain.post.repository;

import Daybreak.server.domain.post.entity.Kind;
import Daybreak.server.domain.post.entity.Post;
import Daybreak.server.domain.post.entity.Status;
import Daybreak.server.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT p FROM Post p WHERE p.id = :id")
    Optional<Post> findById(@Param("id") Long id);

    @Query(value = "SELECT p FROM Post p WHERE p.id = :id AND p.status = :status AND p.member = :member")
    Optional<Post> findByIdAndCheckStatus(@Param("id") Long id, @Param("status") Status status, @Param("member") Member member);

    @Query(value = "DELETE FROM Post p WHERE p.id = :id")
    void deleteById(@Param("id") Long id);
}
