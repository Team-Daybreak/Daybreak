package Daybreak.server.domain.post.entity;

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
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "MEMBER_ID")
//    @JsonIgnore
//    private User user;

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
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Post of(String title, String content) {
        return new Post(title, content);
    }

    // --- Functions ---
    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
        this.getModifiedAt() = LocalDateTime.now();
    }
}
