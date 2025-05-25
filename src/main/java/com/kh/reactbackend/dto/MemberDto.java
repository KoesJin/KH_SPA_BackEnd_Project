package com.kh.reactbackend.dto;

import com.kh.reactbackend.entity.Member;
import com.kh.reactbackend.enums.StatusEnum;
import lombok.*;

import java.time.LocalDateTime;

public class MemberDto {

    @Getter // 직렬화용
    @Setter // 세터와 기본생성자는 역직렬화(json -> 자바객체)용. 세터 없으면 필드 직접접근방식 이용
    @NoArgsConstructor
    //반대로 @Getter도 없고 @Setter도 없으면 Jackson이 해당 필드를 무시할 수 있기 때문에 값이 안 들어간다.
    public static class Create {
        private String user_id;
        private String user_pw;
        private String user_name;
        private String phone;

        public Member toEntity() {
            return Member.builder()
                    .userId(user_id)
                    .userPw(user_pw)
                    .userName(user_name)
                    .phone(phone)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class LoginRequest{
        private String user_id;
        private String user_pw;

        public Member toEntity() {
            return Member.builder()
                    .userId(user_id)
                    .userPw(user_pw)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ChangeInfoRequest{
        private String user_id;
        private String user_name;
        private String phone;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ChangePwRequest{
        private String user_id;
        private String current_pw;
        private String new_pw;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class DeleteRequest{
        private String user_id;
        private String user_pw;
    }


    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private String user_id;
        private String user_name;
        private String phone;
        private LocalDateTime enroll_date;
        private StatusEnum.Status status;

        public static Response simpleDto(Member member) {
            return Response.builder()
                    .user_id(member.getUserId())
                    .user_name(member.getUserName())
                    .build();
        }

        public static Response toDto(Member member) {
            return Response.builder()
                    .user_id(member.getUserId())
                    .user_name(member.getUserName())
                    .phone(member.getPhone())
                    .enroll_date(member.getEnrollDate())
                    .status(member.getStatus())
                    .build();
        }


    }
}
