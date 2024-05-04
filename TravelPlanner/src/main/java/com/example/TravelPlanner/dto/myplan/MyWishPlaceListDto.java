package com.example.TravelPlanner.dto.myplan;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyWishPlaceListDto {
    @NotNull
    private String userid;
    private Long placeid;
}
