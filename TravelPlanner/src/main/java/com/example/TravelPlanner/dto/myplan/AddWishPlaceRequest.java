package com.example.TravelPlanner.dto.myplan;

import com.example.TravelPlanner.dto.User.MemberDto;
import com.example.TravelPlanner.dto.travelplan.TravelPlanDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddWishPlaceRequest {
    private MemberDto memberDto;
    private TravelPlanDto travelPlanDto;
}