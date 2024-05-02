package com.example.TravelPlanner.service.mytravelplan.viewMyWishPlaceList;

import com.example.TravelPlanner.dto.myplan.ViewMyWishPlaceListDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserWishPlaceListViewService {
    Optional<List<ViewMyWishPlaceListDto>> findMyWishPlaceList(String userid);
}
