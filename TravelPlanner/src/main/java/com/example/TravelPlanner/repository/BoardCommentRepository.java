package com.example.TravelPlanner.repository;

import com.example.TravelPlanner.domain.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {
    List<BoardComment> findByBoardId(int boardId);
}
