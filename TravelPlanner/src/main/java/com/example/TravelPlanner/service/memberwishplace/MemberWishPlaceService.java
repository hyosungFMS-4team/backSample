package com.example.TravelPlanner.service.memberwishplace;

import com.example.TravelPlanner.dto.wishPlace.MemberWishPlaceDto;

import java.util.List;

public interface MemberWishPlaceService {
    boolean addWishPlace(String memberId, Long placeId); // 수정 전: void addWishPlace(String memberId, Long placeId);
    boolean deleteWishPlace(String memberId, Long placeId);
    List<MemberWishPlaceDto> getWishList(String memberId);
}