package com.example.TravelPlanner.service.travelplan;

import com.example.TravelPlanner.domain.MemberTravelPlan;
import com.example.TravelPlanner.domain.MemberTravelPlanId;
import com.example.TravelPlanner.dto.travelplan.TravelPlanDto;
import com.example.TravelPlanner.dto.travelplan.ViewMyTravelPlanDto;
import com.example.TravelPlanner.repository.MemberTravelPlanRepository;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ViewMyTravelPlanServiceImpl implements ViewMyTravelPlanService {
    private final MemberTravelPlanRepository memberTravelPlanRepository;


    public ViewMyTravelPlanServiceImpl(MemberTravelPlanRepository memberTravelPlanRepository) {
        this.memberTravelPlanRepository = memberTravelPlanRepository;
    }

    @Override
    @Transactional
    public Optional<List<ViewMyTravelPlanDto>> getTravelPlansByMemberId(String memberId) {
        List<MemberTravelPlan> memberTravelPlans = memberTravelPlanRepository.findAllByMemberMemberId(memberId);
        return Optional.of(memberTravelPlans.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    private ViewMyTravelPlanDto convertToDto(MemberTravelPlan memberTravelPlan) {
        ViewMyTravelPlanDto viewMyTravelPlanDto = new ViewMyTravelPlanDto();
        viewMyTravelPlanDto.setMemberId(memberTravelPlan.getMember().getMemberId());

        TravelPlanDto travelPlanDto = new TravelPlanDto();
        travelPlanDto.setTravelPlanId(memberTravelPlan.getTravelPlan().getTravelPlanId());
        travelPlanDto.setTravelPlanTitle(memberTravelPlan.getTravelPlan().getTravelPlanTitle());
        travelPlanDto.setTravelPlanContent(memberTravelPlan.getTravelPlan().getTravelPlanContent());
        travelPlanDto.setTravelStartedAt(memberTravelPlan.getTravelPlan().getTravelStartedAt());
        travelPlanDto.setTravelEndedAt(memberTravelPlan.getTravelPlan().getTravelEndedAt());
        travelPlanDto.setIsPublic(memberTravelPlan.getTravelPlan().getIsPublic());

        viewMyTravelPlanDto.setTravelPlan(travelPlanDto);

        return viewMyTravelPlanDto;
    }
}
