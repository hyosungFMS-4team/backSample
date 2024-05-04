package com.example.TravelPlanner.service.signup;

import com.example.TravelPlanner.domain.Member;
import com.example.TravelPlanner.dto.signup.SignUpDto;
import com.example.TravelPlanner.dto.signup.ValidateMemberIdDto;
import com.example.TravelPlanner.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final MemberRepository memberRepository;

    @Autowired
    public SignUpServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public SignUpDto join(SignUpDto signUpDto) {
        // DTO를 Entity로 변환하여 저장
        Member member = new Member();
        member.setMemberId(signUpDto.getMemberId());
        member.setPassword(signUpDto.getPassword());
        member.setEmail(signUpDto.getEmail());
        member.setName(signUpDto.getPassword());
        member.setGender(signUpDto.getGender());
        member.setPhone(signUpDto.getPhone());
        member.setAge(signUpDto.getAge());
        memberRepository.save(member);

        // 로그 확인
        System.out.println("회원가입 완료: " + member);

        return signUpDto;
    }


    @Override
    public ValidateMemberIdDto checkMemberId(ValidateMemberIdDto validateMemberIdDto) {
        boolean exists = memberRepository.existsByMemberId(validateMemberIdDto.getMemberId());
        validateMemberIdDto.setExists(exists);
        return validateMemberIdDto;
    }
}
