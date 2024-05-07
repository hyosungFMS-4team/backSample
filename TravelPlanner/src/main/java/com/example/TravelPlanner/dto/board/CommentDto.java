package com.example.TravelPlanner.dto.board;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private int commentId;
    private int boardId;
    private String commentContent;
    private String memberId;
}
