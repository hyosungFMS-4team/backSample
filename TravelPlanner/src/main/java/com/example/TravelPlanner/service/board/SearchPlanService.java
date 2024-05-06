package com.example.TravelPlanner.service.board;

import com.example.TravelPlanner.dto.board.BoardDto;
import java.util.List;

public interface SearchPlanService {
    List<BoardDto> searchPlan(String searchTerm);
}
