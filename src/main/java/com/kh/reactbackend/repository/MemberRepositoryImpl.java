package com.kh.reactbackend.repository;

import com.kh.reactbackend.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext // em 주입
    private EntityManager em;

    // 회원추가
    @Override
    public void save(Member member) {
        em.persist(member);
    }

    // 로그인
    @Override
    public Optional<Member> findMember(String userId) {
        return Optional.ofNullable(em.find(Member.class, userId));
    }
}
