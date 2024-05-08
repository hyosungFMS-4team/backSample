package com.example.TravelPlanner.dto.travelplan;

import com.example.TravelPlanner.domain.TravelPlan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewMyTravelPlanDto {
    private TravelPlanDto travelPlan;
    private String memberId;
}
