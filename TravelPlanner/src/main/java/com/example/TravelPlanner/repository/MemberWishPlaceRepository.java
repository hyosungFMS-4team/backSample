package com.example.TravelPlanner.repository;

import com.example.TravelPlanner.domain.MemberWishPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemberWishPlaceRepository extends JpaRepository<MemberWishPlace, Long> {
    List<MemberWishPlace> findAllByMemberMemberId(String memberId);
    boolean existsByMember_MemberIdAndPlace_PlaceId(String memberId, Long placeId);
    void deleteByMemberMemberIdAndPlacePlaceId(String memberId, Long placeId);


    boolean existsByMemberMemberIdAndPlacePlaceId(String memberId, Long placeId);

}