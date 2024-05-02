//package com.example.TravelPlanner.service.mytravelplan.viewMyWishPlaceList;
//
//import com.example.TravelPlanner.domain.UserWishPlace;
//import com.example.TravelPlanner.dto.myplan.ViewMyWishPlaceListDto;
//import com.example.TravelPlanner.repository.UserWishPlaceRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//// 나중에 nosql db도 사용하면 조회속도를 위해서 nosql db에 넘긴다음 조회하는 걸로 바꿔도 나쁘지 않을듯
//public class UserWishPlaceListViewServiceImpl implements UserWishPlaceListViewService{
//    private final UserWishPlaceRepository userWishPlaceRepository;
//
//    @Override
//    public Optional<List<ViewMyWishPlaceListDto>> findMyWishPlaceList(String userid) {
//        List<ViewMyWishPlaceListDto> myWishListDtos = new ArrayList<>();
//        List<UserWishPlace> myWishPlaceList = userWishPlaceRepository.findAllByUserid(userid);
//
//        if (myWishPlaceList.isEmpty()) {
//            return Optional.empty();
//        }
//
//        for (UserWishPlace w : myWishPlaceList){
//            ViewMyWishPlaceListDto viewMyWishListDto = ViewMyWishPlaceListDto.builder()
//                    .subsr(w.getUserid())
//                    .contentId(w.getPlaceId())
//                    .wish(w.getWish())
//                    .build();
//            myWishListDtos.add(viewMyWishPlaceListDto);
//        }
//
//        return Optional.of(myWishPlaceListDtos);
//    }
//
//
//}
