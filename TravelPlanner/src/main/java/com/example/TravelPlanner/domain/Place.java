package com.example.TravelPlanner.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//CREATE TABLE place ( --장소 테이블
//place_id            INT             PRIMARY KEY,
//place_name          VARCHAR2(200)   NOT NULL,
//place_category      VARCHAR2(10)    NOT NULL,
//lat                 NUMBER(8,4)     NOT NULL, --위도
//lng                 NUMBER(8,4)     NOT NULL, --경도
//place_image_path    VARCHAR2(400)   NOT NULL  --장소이미지url
//);


@Entity
@Table(name = "place")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    @Column(name="place_id")
    private Long placeId;

    @Column(name="place_name")
    private String placeName;

    @Column(name="place_category")
    private String placeCategory;

    @Column(name="lat")
    private double lat;

    @Column(name="lng")
    private double lng;

    @Column(name="place_image_path")
    private String placeImagePath;
}
