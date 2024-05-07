package com.example.TravelPlanner.service.travelplan;

import com.example.TravelPlanner.domain.MemberTravelPlan;
import com.example.TravelPlanner.domain.MemberTravelPlanId;
import com.example.TravelPlanner.dto.travelplan.ViewMyTravelPlanDto;
import com.example.TravelPlanner.repository.MemberTravelPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ViewMyTravelPlanServiceImpl implements ViewMyTravelPlanService {
    private final MemberTravelPlanRepository memberTravelPlanRepository;

    public ViewMyTravelPlanServiceImpl(MemberTravelPlanRepository memberTravelPlanRepository) {
        this.memberTravelPlanRepository = memberTravelPlanRepository;
    }

    @Override
    public Optional<List<ViewMyTravelPlanDto>> findAllByMemberId(String memberId) {
        List<MemberTravelPlan> memberTravelPlans = memberTravelPlanRepository.findAllByMemberMemberId(memberId);
        List<ViewMyTravelPlanDto> memberTravelPlansDtos = new ArrayList<>();
        for (MemberTravelPlan memberTravelPlan : memberTravelPlans) {
            ViewMyTravelPlanDto dto = new ViewMyTravelPlanDto();
            dto.setTravelplanId(memberTravelPlan.getTravelPlan().getTravelplanId());
            dto.setTravelplanTitle(memberTravelPlan.getTravelPlan().getTravelplanTitle());
            dto.setTravelplanContent(memberTravelPlan.getTravelPlan().getTravelplanContent());
            dto.setTravelStartedAt(memberTravelPlan.getTravelPlan().getTravelStartedAt());
            dto.setTravelEndedAt(memberTravelPlan.getTravelPlan().getTravelEndedAt());
            dto.setIsPublic(memberTravelPlan.getTravelPlan().getIsPublic());
            dto.setMemberId(memberId);
            memberTravelPlansDtos.add(dto);
        }
        return Optional.of(memberTravelPlansDtos);
    }
}
