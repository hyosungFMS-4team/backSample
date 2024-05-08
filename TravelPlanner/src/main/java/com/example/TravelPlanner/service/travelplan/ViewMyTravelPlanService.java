package com.example.TravelPlanner.service.travelplan;

import com.example.TravelPlanner.dto.travelplan.ViewMyTravelPlanDto;

import java.util.List;
import java.util.Optional;

public interface ViewMyTravelPlanService {
    Optional<List<ViewMyTravelPlanDto>> getTravelPlansByMemberId(String memberId);
}