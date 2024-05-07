package com.example.TravelPlanner.repository;

import com.example.TravelPlanner.domain.MemberTravelPlan;
import com.example.TravelPlanner.domain.MemberTravelPlanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberTravelPlanRepository extends JpaRepository<MemberTravelPlan, MemberTravelPlanId> {
    List<MemberTravelPlan> findAllByMemberMemberId(String memberId);
}
