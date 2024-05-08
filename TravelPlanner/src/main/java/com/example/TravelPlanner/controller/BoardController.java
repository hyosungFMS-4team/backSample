package com.example.TravelPlanner.controller;

import com.example.TravelPlanner.dto.board.BoardDto;
import com.example.TravelPlanner.service.board.SearchPlanServiceImpl;
import com.example.TravelPlanner.service.board.ViewAllBoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {

    private final SearchPlanServiceImpl searchPlanService;
    private final ViewAllBoardServiceImpl viewAllBoardService;

    @Autowired
    public BoardController(SearchPlanServiceImpl searchPlanService, ViewAllBoardServiceImpl viewAllBoardService) {
        this.searchPlanService = searchPlanService;
        this.viewAllBoardService = viewAllBoardService;
    }

    @GetMapping("/allboard")
    public ResponseEntity<List<BoardDto>> getAllBoards() {
        List<BoardDto> boards = viewAllBoardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }


    @GetMapping("/search/{input}")
    public ResponseEntity<List<BoardDto>> searchPlans(@PathVariable("input") String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            // 사용자가 아무것도 입력하지 않은 경우
            return ResponseEntity.badRequest().body(null);
        }

        List<BoardDto> foundPlans = searchPlanService.searchPlan(searchTerm);

        // 검색 결과 반환
        return ResponseEntity.ok().body(foundPlans);
    }

}

