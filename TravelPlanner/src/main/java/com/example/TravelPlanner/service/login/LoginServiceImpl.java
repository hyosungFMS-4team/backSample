package com.example.TravelPlanner.service.login;

import com.example.TravelPlanner.domain.Member;
import com.example.TravelPlanner.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
//파일은 하나의 역할만 담당
//서비스 인터페이스는 비즈니스 로직에 대한 추상화 제공
public class LoginServiceImpl implements LoginService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean validateMember(String memberId, String password) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);
        System.out.println("Member ID: " + memberId);
        System.out.println("Password: " + password);

        if (!optionalMember.isPresent()) {
            System.out.println("User not found");
            return false;
        }

        Member member = optionalMember.get();

        if (bCryptPasswordEncoder.matches(password, member.getPassword())) {
            System.out.println("Login successful");
            return true;
        } else {
            System.out.println("correct: " + member.getPassword());
            System.out.println("input: " + password);
            System.out.println("Incorrect password");
            return false;
        }
    }


}
