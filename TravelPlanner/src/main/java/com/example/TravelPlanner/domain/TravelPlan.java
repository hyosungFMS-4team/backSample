package com.example.TravelPlanner.domain;


//CREATE TABLE place (


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

//        place_id NUMBER NOT NULL,
//        place_name varchar2(200),
//category varchar2(10),
//lat double precision,
//lng double precision,
//place_image_path varchar2(400)
//);
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "place")
@Getter
@Setter
@Builder
public class TravelPlan {

    @Id
    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "category")
    private String category;

    @Column(name = "lat")
    private Double latitude;

    @Column(name = "lng")
    private Double longitude;

    @Column(name = "place_image_path")
    private String placeImagePath;
}
