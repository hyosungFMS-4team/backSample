package com.example.TravelPlanner.service.board;

import com.example.TravelPlanner.dto.board.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentsByBoardId(int boardId);
    CommentDto createComment(CommentDto commentDto);
    CommentDto updateComment(CommentDto commentDto);
    void deleteComment(int commentId);
}
