package Daybreak.server.domain.post.entity;

import Daybreak.server.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 50)
    private String title;

    @Setter
    @Column(nullable = false)
    private String content;

    // --- Relation Mapping ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @JsonIgnore
    private Member member;

    @Setter
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Kind kind;

    @Setter
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.ACTIVE;

    @CreatedDate
    @Column(updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private Post(String title, String content, Kind kind, Member member) {
        this.title = title;
        this.content = content;
        this.kind = kind;
        this.member = member;
    }

    public static Post createOf(String title, String content, Kind kind, Member member) {
        return new Post(title, content, kind, member);
    }

    // --- Functions ---
    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
        this.modifiedAt = LocalDateTime.now();
    }
}
