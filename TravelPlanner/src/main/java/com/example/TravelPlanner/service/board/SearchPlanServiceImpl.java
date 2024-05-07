package com.example.TravelPlanner.service.board;

import com.example.TravelPlanner.domain.Board;
import com.example.TravelPlanner.dto.board.BoardDto;
import com.example.TravelPlanner.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchPlanServiceImpl implements SearchPlanService {


    private final BoardRepository boardRepository;

    public SearchPlanServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<BoardDto> searchPlan(String searchTerm) {
        String searchTermWithoutSpaces = searchTerm.replaceAll("\\s+", "");

        List<Board> allBoards = boardRepository.findAll();

        // 검색어와 매칭되는 Board 찾기
        List<Board> foundBoards = allBoards.stream()
                .filter(board -> {
                    if (board.getBoardTitle() == null) {
                        return false;
                    }
                    String titleWithoutSpaces = board.getBoardTitle().replaceAll("\\s+", "");
                    return titleWithoutSpaces.toLowerCase().contains(searchTermWithoutSpaces.toLowerCase());
                })
                .collect(Collectors.toList());

        return foundBoards.stream()
                .map(board -> BoardDto.builder()
                        .boardId(board.getBoardId())
                        .build())
                .collect(Collectors.toList());
    }


}

