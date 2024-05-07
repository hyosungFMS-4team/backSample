package com.example.TravelPlanner.controller;

import com.example.TravelPlanner.dto.board.CommentDto;
import com.example.TravelPlanner.service.board.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class BoardCommentController {

    private final CommentServiceImpl commentService;
    public BoardCommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<List<CommentDto>> getCommentsByBoardId(@PathVariable int boardId) {
        List<CommentDto> comments = commentService.getCommentsByBoardId(boardId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        CommentDto createdComment = commentService.createComment(commentDto);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateComment(commentDto);
        if (updatedComment != null) {
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int commentId) {
        System.out.println("삭제된 comment 정보 = " + commentId);
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
