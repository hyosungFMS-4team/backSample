package com.example.TravelPlanner.dto.wishPlace;

import com.example.TravelPlanner.dto.User.MemberDto;
import com.example.TravelPlanner.dto.travelplan.PlaceDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddWishPlaceRequest {
    private MemberDto memberDto;
    private PlaceDto placeDto;
}
