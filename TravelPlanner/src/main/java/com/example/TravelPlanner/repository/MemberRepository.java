package com.example.TravelPlanner.repository;

import com.example.TravelPlanner.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//서비스로부터 DB에 insert 요청이 오면 VO객체들을 영속성 컨테이너라는 곳에 넣어놓았다가 로직이 끝나면 한번에 DB로 커밋
// = DB와 통신이라고 생각
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT u FROM Member u WHERE u.memberId = :member_id")
    Optional<Member> findByMemberId(@Param("member_id") String memberId);

    boolean existsByMemberId(String memberId);

}
