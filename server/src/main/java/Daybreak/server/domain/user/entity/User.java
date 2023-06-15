package Daybreak.server.domain.user.entity;

import Daybreak.server.domain.comment.entity.Comment;
import Daybreak.server.domain.post.entity.Post;
import Daybreak.server.domain.subscribe.entity.Subscribe;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, updatable = false)
    private String id;

    @Column(nullable = false)
    @Size(min = 8, max = 20)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickName;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /**회원이 탈퇴하면 해당 회원이 작성한 포스트도 전부 삭제되어야 한다.*/
    @OneToMany(mappedBy = "User", cascade = CascadeType.REMOVE)
    private List<Post> postList = new ArrayList<>();

    /**회원이 탈퇴하면 해당 회원이 구독한 정보도 전부 삭제되어야 한다.*/
    @OneToMany(mappedBy = "User", cascade = CascadeType.REMOVE)
    private List<Subscribe> subscribeList = new ArrayList<>();

    /**회원이 탈퇴해도 해당 회원이 작성한 댓글 이력은 남아있어야 한다.
     Ex) 탈퇴된 회원의 댓글입니다.*/
    @OneToMany(mappedBy = "User")
    private List<Comment> commentList = new ArrayList<>();



}
