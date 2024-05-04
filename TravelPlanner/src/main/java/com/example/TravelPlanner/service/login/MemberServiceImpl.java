package com.example.TravelPlanner.service.login;

import com.example.TravelPlanner.domain.Member;
import com.example.TravelPlanner.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;


//나중에 파일 다 지우자
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public void printAllMembersToConsole() {
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            System.out.println(member);
        }
    }
}
