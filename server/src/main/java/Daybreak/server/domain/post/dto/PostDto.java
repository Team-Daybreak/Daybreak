package Daybreak.server.domain.post.dto;

import Daybreak.server.domain.post.entity.Kind;
import Daybreak.server.domain.post.entity.Status;
import Daybreak.server.domain.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

public class PostDto {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Post {
        private String title;
        private String content;
        private Kind kind;

        public static PostDto.Post of(String title, String content, Kind kind) {
            return new PostDto.Post(title, content, kind);
        }

        public Daybreak.server.domain.post.entity.Post toEntity(Member member) {
            return Daybreak.server.domain.post.entity.Post.createOf(title, content, kind, member);
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Patch {
        private String title;
        private String content;

        public static PostDto.Patch of(String title, String content) {
            return new PostDto.Patch(title, content);
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Long id;
        private String title;
        private String content;
        private Kind kind;
        private Status status;
        private LocalDateTime createdAt;

        public static PostDto.Response of(Long id, String title, String content, Kind kind, Status status, LocalDateTime createdAt) {
            return new PostDto.Response(id, title, content, kind, status, createdAt);
        }

        public static PostDto.Response fromEntity(Daybreak.server.domain.post.entity.Post entity) {
            return new PostDto.Response(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getContent(),
                    entity.getKind(),
                    entity.getStatus(),
                    entity.getCreatedAt()
            );
        }
    }
}
