package com.kh.reactbackend.service;

import com.kh.reactbackend.dto.MemberDto;
import com.kh.reactbackend.entity.Member;
import com.kh.reactbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // 회원추가
    @Override
    public String addMember(MemberDto.Create memberDto) {

        // Dto -> Entity
        Member member = memberDto.toEntity();

        // Repository 전달
        memberRepository.save(member);

        // 영속상태에 관리되는 member의 Id 가져오기 -> DB에 저장된 상태에 있는 member
        return member.getUserId();
    }

    // 로그인
    @Transactional(readOnly = true)
    @Override
    public MemberDto.Response loginMember(MemberDto.LoginRequest memberDto) {
        // Dto -> Entity
        Member member = memberDto.toEntity();

        // userId 통해 회원 정보 가져오기
        Member result = memberRepository.findMember(member.getUserId())
                // 여기서 orElse()를 사용하여 400에러로 보내야하는지, orElseThrow()를 이용해야하는지?
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다.")); // 서버 500에러 -> catch 블럭으로 이동
                //.orElse(null);

        // userId를통해 Id값이 존재하고, 입력한 비밀번호와 userId통해 가져온 회원정보의 비밀번호가 같은지 확인
        if (result != null && memberDto.getUser_pw().equals(result.getUserPw())) {
            return MemberDto.Response.toDto(result);
        } else {
            return null; // 클라이언트 400 에러 -> catch 블럭으로 이동
        }
    }


    // 아이디 중복확인
    @Transactional(readOnly = true)
    @Override
    public MemberDto.Response checkMember(String userId) {
        return memberRepository.findMember(userId)
                .map(MemberDto.Response::simpleDto)
                .orElse(null); // 값이 없으면 null 반환 -> null 반환안하고 에러 던지면 프론트 콘솔에 에러남
    }

    // 회원 정보 변경
    @Override
    public MemberDto.Response changeInfo(MemberDto.ChangeInfoRequest request) {
        // Member 가져오기
        Member member = memberRepository.findMember(request.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // 엔티티는 Dto 종속적이지 않게 작성 -> 엔티티 안에 Dto 넣지말기
        member.updateMemberInfo(request.getUser_name(),request.getPhone());

        return MemberDto.Response.toDto(member);
    }

    // 비밀번호 변경
    @Override
    public MemberDto.Response changePw(MemberDto.ChangePwRequest request) {
        // Member 가져오기
        Member member = memberRepository.findMember(request.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // Member의 비밀번호와 입력한 현재 비밀번호가 같지 않을경우
        if(!request.getCurrent_pw().equals(member.getUserPw())){
            return null;
        }

        member.updateMemberPw(request.getNew_pw());

        return MemberDto.Response.toDto(member);
    }

    @Override
    public String deleteMember(MemberDto.DeleteRequest request) {
        // Member 가져오기
        Member member = memberRepository.findMember(request.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // Member의 비밀번호와 입력한 현재 비밀번호가 같지 않을경우
        if(!request.getUser_pw().equals(member.getUserPw())){
            return "비밀번호가 일치하지 않습니다.";
        }

        member.deleteMember();

        return null;
    }


}
