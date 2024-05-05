package com.example.TravelPlanner.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member_wishplace")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberWishPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "place_id")
    private Place place;
}
//public class MemberWishPlace {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "member_id") // member_id 컬럼에 매핑
//    private String memberId;
//
//    @Column(name = "place_id") // place_id 컬럼에 매핑
//    private Long placeId;
//
//    // 기존과 동일하게 Member 및 Place 엔티티와의 관계 설정
//    @ManyToOne
//    @JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
//    private Member member;
//
//    @ManyToOne
//    @JoinColumn(name = "place_id", referencedColumnName = "place_id", insertable = false, updatable = false)
//    private Place place;


//}
