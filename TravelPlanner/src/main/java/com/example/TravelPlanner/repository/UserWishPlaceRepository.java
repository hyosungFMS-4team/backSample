package com.example.TravelPlanner.repository;

import com.example.TravelPlanner.domain.UserWishPlace;

import java.util.List;

public interface UserWishPlaceRepository {
    List<UserWishPlace> findAllByUserid(String subsr);
}
