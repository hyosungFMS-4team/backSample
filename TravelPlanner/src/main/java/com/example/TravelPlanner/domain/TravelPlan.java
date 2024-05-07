package com.example.TravelPlanner.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "travelplan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelPlan {
    @Id
    @Column(name="travelplan_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String travelplanId;

    @Column(name="travelplan_title")
    private String travelplanTitle;

    @Column(name="travelplan_content")
    private String travelplanContent;

    @Column(name="travel_started_at")
    private Date travelStartedAt;

    @Column(name="travel_ended_at")
    private Date travelEndedAt;

    @Column(name="is_public")
    private int isPublic;

    @Column(name="member_id")
    private String memberId;
}
