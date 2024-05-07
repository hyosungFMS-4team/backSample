package com.example.TravelPlanner.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "boardcomment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardComment {
    @Id
    @Column(name="comment_id")
    private int commentId;

    @Column(name="board_id")
    private int boardId;

    @Column(name="comment_content")
    private String commentContent;

    @Column(name="member_id")
    private String memberId;

}
