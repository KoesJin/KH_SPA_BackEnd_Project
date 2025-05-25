package com.kh.reactbackend.controller;

import com.kh.reactbackend.dto.MemberDto;
import com.kh.reactbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@CrossOrigin // 프론트와 백엔드 url이 다른것을 맞춰주기 위한 어노테이션
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping
    public ResponseEntity<String> addMember(@RequestBody MemberDto.Create memberDto) {
        return ResponseEntity.ok(memberService.addMember(memberDto));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<MemberDto.Response> login(@RequestBody MemberDto.LoginRequest request) {
        return ResponseEntity.ok(memberService.loginMember(request));
    }

    // 아이디 중복체크 (아이디 조회)
    @GetMapping("/check-id")
    public ResponseEntity<MemberDto.Response> checkMember(@RequestParam String userId) {
        MemberDto.Response result = memberService.checkMember(userId);
        return ResponseEntity.ok(result);
    }

    // 회원 정보 변경
    @PatchMapping("/change-info")
    public ResponseEntity<MemberDto.Response> changeInfo(@RequestBody MemberDto.ChangeInfoRequest request) {
        return ResponseEntity.ok(memberService.changeInfo(request));
    }

    // 비밀번호 변경
    @PatchMapping("/change-pw")
    public ResponseEntity<MemberDto.Response> changePw(@RequestBody MemberDto.ChangePwRequest request) {
        return ResponseEntity.ok(memberService.changePw(request));
    }

    // 회원 탈퇴
    @PatchMapping
    public ResponseEntity<String> deleteMember(@RequestBody MemberDto.DeleteRequest request) {
        return ResponseEntity.ok(memberService.deleteMember(request));
    }
}
