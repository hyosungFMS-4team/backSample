package com.example.TravelPlanner.repository;

import com.example.TravelPlanner.domain.Board;
import com.example.TravelPlanner.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAll();
    //List<Board> findByBoardTitle(Board board);

    //List<Board> findByBoardTitle(Class<Board> boardClass);
}
