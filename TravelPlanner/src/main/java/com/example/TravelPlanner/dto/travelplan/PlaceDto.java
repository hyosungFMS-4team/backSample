package com.example.TravelPlanner.dto.travelplan;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
    private Long placeId;
    private String placeName;
    private String placeCategory;
    private double lat;
    private double lng;
    private String placeImagePath;
}
