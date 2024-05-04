package com.example.TravelPlanner.controller;


import com.example.TravelPlanner.dto.User.MemberDto;
import com.example.TravelPlanner.dto.myplan.AddWishPlaceRequest;
import com.example.TravelPlanner.dto.myplan.MyWishPlaceListDto;
import com.example.TravelPlanner.dto.travelplan.TravelPlanDto;
import com.example.TravelPlanner.service.mytravelplan.myWishPlaceList.MemberWishPlaceListServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberWishPlaceController {

    private final MemberWishPlaceListServiceImpl userWishPlaceListService;

    public MemberWishPlaceController(MemberWishPlaceListServiceImpl userWishPlaceListViewService) {
        this.userWishPlaceListService = userWishPlaceListViewService;
    }

//    @PostMapping("/wish")
//    public ResponseEntity<List<MyWishPlaceListDto>> findMyWishList(@RequestBody UserDto userDto){
//        Optional<List<MyWishPlaceListDto>> myWishPlaceListDtos = userWishPlaceListService.findMyWishPlaceList(userDto.getUserid());
//        if (!myWishPlaceListDtos.isPresent() || myWishPlaceListDtos.get().isEmpty()) {
//            return ResponseEntity.ok().body(null);
//        }
//        return ResponseEntity.ok(myWishPlaceListDtos.get());
//    }

    @PostMapping("/wish/add")
    public ResponseEntity<String> addWishPlace(@RequestBody AddWishPlaceRequest request) {
        MemberDto userDto = request.getMemberDto();
        TravelPlanDto travelPlanDto = request.getTravelPlanDto();
        if (userDto == null || travelPlanDto == null) {
            return ResponseEntity.badRequest().body("UserDto or TravelPlanDto is missing in the request body.");
        }
        boolean success = userWishPlaceListService.addWishPlace(userDto.getMemberId(), travelPlanDto.getPlaceId());
        if (success) {
            return ResponseEntity.ok("Wish place added successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add wish place.");
        }
    }

    @GetMapping("/wish/list")
    public ResponseEntity<List<MyWishPlaceListDto>> getWishList(@RequestParam String memberId) {
        List<MyWishPlaceListDto> wishList = userWishPlaceListService.getWishList(memberId);
        if (wishList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(wishList);
        }
    }

    @DeleteMapping("/wish/delete")
    public ResponseEntity<String> deleteWishPlace(@RequestParam String memberId, @RequestParam Long placeId) {
        boolean success = userWishPlaceListService.deleteWishPlace(memberId, placeId);
        if (success) {
            return ResponseEntity.ok("Wish place deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete wish place.");
        }
    }

}
