package Daybreak.server.domain.member.entity;

import Daybreak.server.domain.comment.entity.Comment;
import Daybreak.server.domain.post.entity.Post;
import Daybreak.server.domain.subscribe.entity.Subscribe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true, updatable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickName;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /**회원이 탈퇴하면 해당 회원이 작성한 포스트도 전부 삭제되어야 한다.*/
    @OneToMany
    private List<Post> postList = new ArrayList<>();

    /**회원이 탈퇴하면 해당 회원이 구독한 정보도 전부 삭제되어야 한다.*/
    @OneToMany
    private List<Subscribe> subscribeList = new ArrayList<>();

    /**회원이 탈퇴해도 해당 회원이 작성한 댓글 이력은 남아있어야 한다.
     Ex) 탈퇴된 회원의 댓글입니다.*/
    @OneToMany
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Member(Long memberId, String loginId, String password, String nickName, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.loginId = loginId;
        this.password = password;
        this.nickName = nickName;
        this.createdAt = createdAt;
    }
}
