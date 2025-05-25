package com.kh.reactbackend.entity;

import com.kh.reactbackend.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
public class Member {
    @Id
    @Column(name = "USER_ID", length = 30)
    private String userId;

    @Column(name = "USER_PW", length = 100, nullable = false)
    private String userPw;

    @Column(name = "USER_NAME", length = 5, nullable = false)
    private String userName;

    @Column(name = "PHONE", length = 11, nullable = false)
    private String phone;

    @Column(name = "ENROLL_DATE")
    private LocalDateTime enrollDate;

    @Column(name = "MODIFY_DATE")
    private LocalDateTime modifyDate;

    @Column(name = "STATUS", length = 1, nullable = false)
    @Enumerated(EnumType.STRING) // Enum이 선언된 상수의 이름(Y,N)을 String 클래스 타입으로 변환하여 DB에 저장하겠다.
    private StatusEnum.Status status;

    public void updateMemberInfo(String userName, String phone) {
        this.userName = userName;
        this.phone = phone;
    }

    public void updateMemberPw(String newPw) {
        this.userPw = newPw;
    }

    public void deleteMember() {
        this.status = StatusEnum.Status.N;
    }

    @PrePersist
    public void prePersist() {
        enrollDate = LocalDateTime.now();
        modifyDate = LocalDateTime.now();
        if(status == null) {
            status = StatusEnum.Status.Y;
        }
    }

    // 이거 사용안하고 update시에 어떻게 되는지 확인해보기
    // 사용 안할시 update시에 변경되지 않음
    @PreUpdate
    public void preUpdate() {
        this.modifyDate = LocalDateTime.now();
    }



}
