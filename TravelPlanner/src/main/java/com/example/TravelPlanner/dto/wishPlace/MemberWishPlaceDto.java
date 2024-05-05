package com.example.TravelPlanner.dto.wishPlace;

import com.example.TravelPlanner.domain.Member;
import com.example.TravelPlanner.domain.Place;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberWishPlaceDto {
    private String memberId;
    private Long placeId;
}
