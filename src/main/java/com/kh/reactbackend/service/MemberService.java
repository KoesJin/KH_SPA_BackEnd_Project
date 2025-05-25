package com.kh.reactbackend.service;

import com.kh.reactbackend.dto.MemberDto;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    // 회원추가
    String addMember(MemberDto.Create memberDto);

    // 로그인
    MemberDto.Response loginMember(MemberDto.LoginRequest memberDto);

    // 아이디 중복확인
    MemberDto.Response checkMember(String userId);

    // 회원 정보 변경
    MemberDto.Response changeInfo(MemberDto.ChangeInfoRequest request);

    // 비밀번호 변경
    MemberDto.Response changePw(MemberDto.ChangePwRequest request);

    // 회원 탈퇴
    String deleteMember(MemberDto.DeleteRequest request);
}
