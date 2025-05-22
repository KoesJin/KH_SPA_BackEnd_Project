package com.kh.reactbackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", length = 30)
    private String userId;

    @Column(name = "USER_PW", length = 100, nullable = false)
    private String userPw;


}
