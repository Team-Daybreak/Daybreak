package Daybreak.server.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class MemberDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z]{6,16}$",
                 message = "아이디는 6 ~ 20 글자여야 합니다.")
        private String id;

        @NotBlank
        @Pattern(regexp = "^[#?!@$%^&*a-zA-Z0-9]{8,16}$",
                message = "비밀번호는 8 ~ 16 글자여야 합니다.")
        private String password;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Zㄱ-힣0-9]{2,12}$",
                message = "닉네임은 최소 2글자 이상 12글자 이하여야 합니다. 또, 특수문자 및 공백은 포함될 수 없습니다.")
        private String nickName;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        @NotBlank
        @Pattern(regexp = "^[#?!@$%^&*a-zA-Z0-9]{8,16}$",
                message = "비밀번호는 8 ~ 16 글자여야 합니다.")
        private String password;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Zㄱ-힣0-9]{2,12}$",
                message = "닉네임은 최소 2글자 이상 12글자 이하여야 합니다. 또, 특수문자 및 공백은 포함될 수 없습니다.")
        private String nickName;
    }

    @Getter
    public static class Response {

        private String id;

        private String password;

        private String nickName;

        private LocalDateTime createdAt;

        @Builder
        public Response(String id, String password, String nickName, LocalDateTime createdAt) {
            this.id = id;
            this.password = password;
            this.nickName = nickName;
            this.createdAt = createdAt;
        }
    }
}
