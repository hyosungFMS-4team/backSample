package com.example.TravelPlanner.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_travelplan")
@Getter
@NoArgsConstructor
@IdClass(MemberTravelPlanId.class)
public class MemberTravelPlan {
    @Id
    @ManyToOne
    @JoinColumn(name = "travelplan_id")
    private TravelPlan travelPlan;

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
