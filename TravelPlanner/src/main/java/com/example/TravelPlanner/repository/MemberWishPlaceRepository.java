package com.example.TravelPlanner.repository;

import com.example.TravelPlanner.domain.MemberWishPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberWishPlaceRepository extends JpaRepository<MemberWishPlace, String> {
    List<MemberWishPlace> findAllByMemberId(String memberId);

    boolean existsByMemberIdAndPlaceId(String memberId, Long placeId);

    void deleteByMemberIdAndPlaceId(String memberId, Long placeId);
}
