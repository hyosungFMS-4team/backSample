package com.example.TravelPlanner.dto.travelplan;

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
    private String travelplanId;
    private String travelplanTitle;
    private String travelplanContent;
    private Date travelStartedAt;
    private Date travelEndedAt;
    private int isPublic;
    private String memberId;
}
