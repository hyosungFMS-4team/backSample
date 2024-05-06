package com.example.TravelPlanner.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @Column(name="board_id")
    private Long boardId;

    @Column(name="board_title")
    private String boardTitle;

    @Column(name="board_content")
    private String boardContent;

    @Column(name="board_created_at")
    private Date boardCreatedAt;

    @Column(name="member_id")
    private String memberId;

    @Column(name="travelplan_id")
    private String travelplanId;

}
