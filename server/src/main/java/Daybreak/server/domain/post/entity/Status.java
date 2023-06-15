package Daybreak.server.domain.post.entity;

import lombok.Getter;

public enum Status {
    ACTIVE("등록"), DELETED("삭제");

    @Getter
    private String status;

    Status(String status) {
        this.status = status;
    }
}
