package com.example.TravelPlanner.repository;

import com.example.TravelPlanner.domain.MemberTravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberTravelPlanRepository extends JpaRepository<MemberTravelPlan, Long> {
    List<MemberTravelPlan> findAllByMemberMemberId(String memberId);
}
