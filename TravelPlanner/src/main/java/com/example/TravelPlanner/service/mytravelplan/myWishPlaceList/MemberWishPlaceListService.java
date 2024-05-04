package com.example.TravelPlanner.service.mytravelplan.myWishPlaceList;

import com.example.TravelPlanner.dto.myplan.MyWishPlaceListDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberWishPlaceListService {
    boolean addWishPlace(String memberId, Long placeId);
    List<MyWishPlaceListDto> getWishList(String memberId);
    boolean deleteWishPlace(String memberId, Long placeId);
//    Optional<List<MyWishPlaceListDto>> findMyWishPlaceList(String userid);
}
