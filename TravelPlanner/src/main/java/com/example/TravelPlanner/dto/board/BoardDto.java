package com.example.TravelPlanner.dto.board;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private Date boardCreatedAt;
    private String memberId;
    private String travelPlanId;
}
