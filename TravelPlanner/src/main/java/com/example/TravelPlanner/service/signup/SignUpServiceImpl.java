package com.example.TravelPlanner.service.signup;

import com.example.TravelPlanner.domain.Member;
import com.example.TravelPlanner.dto.signup.SignUpDto;
import com.example.TravelPlanner.dto.signup.ValidateMemberIdDto;
import com.example.TravelPlanner.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SignUpServiceImpl implements SignUpService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SignUpServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
//    @Autowired
//    public SignUpServiceImpl(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Override
    public SignUpDto signup(SignUpDto signUpDto) {
        // 회원 아이디의 중복 여부를 확인
        //boolean exists = memberRepository.existsByMemberId(signUpDto.getMemberId());

        // 중복된 아이디가 존재하는 경우
//        if (exists) {
//            System.out.println("중복된 회원 아이디입니다: " + signUpDto.getMemberId());
//            return null;
//        }
        // DTO를 Entity로 변환하여 저장
        Member member = new Member();
        member.setMemberId(signUpDto.getMemberId());
        // 비밀번호 해싱하여 저장
        String hashedPassword = bCryptPasswordEncoder.encode(signUpDto.getPassword());
        member.setPassword(hashedPassword);
//        member.setPassword(signUpDto.getPassword());
        member.setEmail(signUpDto.getEmail());
        member.setName(signUpDto.getName());
        member.setGender(signUpDto.getGender());
        member.setPhone(signUpDto.getPhone());
        member.setAge(signUpDto.getAge());
        //System.out.println("회원가입 아이디 : " + member.getMemberId());
        memberRepository.save(member);

        // 로그 확인
        System.out.println("회원가입 완료: " + member);

        return signUpDto;
    }

//    @Override
//    public ValidateMemberIdDto checkMemberId(ValidateMemberIdDto validateMemberIdDto) {
//        boolean exists = memberRepository.existsByMemberId(validateMemberIdDto.getMemberId());
//        validateMemberIdDto.setExists(exists);
//        return validateMemberIdDto;
//    }
//    @Override
//    public ValidateMemberIdDto checkMemberIdExists(String memberId) {
//        boolean exists = memberRepository.existsByMemberId(memberDto.getMemberId());
//        validateMemberIdDto.setExists(exists);
//        return validateMemberIdDto;
//    }

    @Override
    public boolean checkMemberId(String memberId) {
        // memberId가 DB에 존재하는지 확인하여 결과 반환
        return memberRepository.existsByMemberId(memberId);
    }
}
