package Daybreak.server.domain.post.entity;

import lombok.Getter;

@Getter
public enum Kind {

    GENERAL("수다");

    @Getter
    private final String status;

    Kind(String status) {
        this.status = status;
    }
}
