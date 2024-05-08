package com.example.TravelPlanner.service.board;

import com.example.TravelPlanner.domain.Board;
import com.example.TravelPlanner.dto.board.BoardDto;
import com.example.TravelPlanner.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewAllBoardServiceImpl implements ViewAllBoardService {
    private final BoardRepository boardRepository;

    public ViewAllBoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<BoardDto> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BoardDto convertToDto(Board board) {
        return BoardDto.builder()
                .boardId(board.getBoardId())
                .boardTitle(board.getBoardTitle())
                .boardContent(board.getBoardContent())
                .boardCreatedAt(board.getBoardCreatedAt())
                .memberId(board.getMemberId())
                .travelPlanId(board.getTravelPlanId())
                .build();
    }

}
