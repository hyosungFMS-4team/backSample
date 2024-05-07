package com.example.TravelPlanner.service.board;

import com.example.TravelPlanner.domain.BoardComment;
import com.example.TravelPlanner.dto.board.CommentDto;
import com.example.TravelPlanner.repository.BoardCommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{
    private final BoardCommentRepository boardCommentRepository;

    public CommentServiceImpl(BoardCommentRepository boardCommentRepository) {
        this.boardCommentRepository = boardCommentRepository;
    }

    @Override
    public List<CommentDto> getCommentsByBoardId(int boardId) {
        List<BoardComment> boardComments = boardCommentRepository.findByBoardId(boardId);
        return boardComments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        BoardComment boardComment = convertToEntity(commentDto);
        BoardComment savedComment = boardCommentRepository.save(boardComment);
        return convertToDto(savedComment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto) {
        Optional<BoardComment> optionalComment = boardCommentRepository.findById(commentDto.getCommentId());
        if (optionalComment.isPresent()) {
            BoardComment existingComment = optionalComment.get();
            existingComment.setCommentContent(commentDto.getCommentContent());
            // 필요에 따라 다른 필드 업데이트
            BoardComment updatedComment = boardCommentRepository.save(existingComment);
            return convertToDto(updatedComment);
        }
        return null; // 혹은 예외 처리
    }

    @Override
    @Transactional
    public void deleteComment(int commentId) {
        System.out.println("댓글이 삭제되었습니다. 삭제된 댓글 ID: " + commentId);
        boardCommentRepository.deleteById(commentId);
    }

    private CommentDto convertToDto(BoardComment boardComment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(boardComment.getCommentId());
        commentDto.setBoardId(boardComment.getBoardId());
        commentDto.setCommentContent(boardComment.getCommentContent());
        commentDto.setMemberId(boardComment.getMemberId());
        return commentDto;
    }

    private BoardComment convertToEntity(CommentDto commentDto) {
        BoardComment boardComment = new BoardComment();
        boardComment.setCommentId(commentDto.getCommentId());
        boardComment.setBoardId(commentDto.getBoardId());
        boardComment.setCommentContent(commentDto.getCommentContent());
        boardComment.setMemberId(commentDto.getMemberId());
        return boardComment;
    }


}
