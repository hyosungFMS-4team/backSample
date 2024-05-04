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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_wishplace")
@Getter
@Setter
public class MemberWishPlace {
    @Id
    @Column(name="member_id")
    private String memberId;

    @Column(name="place_id")
    private Long placeId;
}
