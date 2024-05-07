package com.example.TravelPlanner.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberTravelPlanId implements Serializable {

    private String travelPlan; // TravelPlan의 travelplanId를 참조

    private String member; // Member의 memberId를 참조

    // equals, hashCode 메서드 오버라이딩
}

