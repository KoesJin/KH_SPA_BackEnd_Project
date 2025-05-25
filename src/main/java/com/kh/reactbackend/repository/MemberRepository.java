package com.kh.reactbackend.repository;

import com.kh.reactbackend.dto.MemberDto;
import com.kh.reactbackend.entity.Member;

import java.util.Optional;

public interface MemberRepository {

    // 회원추가
    void save(Member member);

    // 로그인
    Optional<Member> findMember(String userId);
}
