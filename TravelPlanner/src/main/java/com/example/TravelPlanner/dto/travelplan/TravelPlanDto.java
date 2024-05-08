package com.example.TravelPlanner.dto.travelplan;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelPlanDto {
    private String travelPlanId;
    private String travelPlanTitle;
    private String travelPlanContent;
    private Date travelStartedAt;
    private Date travelEndedAt;
    private int isPublic;
}
