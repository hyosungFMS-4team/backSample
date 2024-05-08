package com.example.TravelPlanner.controller;

import com.example.TravelPlanner.dto.User.MemberDto;
import com.example.TravelPlanner.dto.travelplan.PlaceDto;
import com.example.TravelPlanner.dto.wishPlace.AddWishPlaceRequest;
import com.example.TravelPlanner.dto.wishPlace.MemberWishPlaceDto;
import com.example.TravelPlanner.service.memberwishplace.MemberWishPlaceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wish")
public class MemberWishPlaceController {


    private final MemberWishPlaceServiceImpl memberWishPlaceService;

    public MemberWishPlaceController(MemberWishPlaceServiceImpl memberWishPlaceService) {
        this.memberWishPlaceService = memberWishPlaceService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addWishPlace(@RequestBody AddWishPlaceRequest addWishPlaceRequest) {
        MemberDto memberDto = addWishPlaceRequest.getMemberDto();
        PlaceDto placeDto = addWishPlaceRequest.getPlaceDto();
        if (memberDto == null || placeDto == null) {
            return ResponseEntity.badRequest().body("MemberDto or PlaceDto is missing in the request body.");
        }
        boolean success = memberWishPlaceService.addWishPlace(memberDto.getMemberId(), placeDto.getPlaceId());
        if (success) {
            return ResponseEntity.ok("Wish place added successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add wish place.");
        }
    }

    @GetMapping("/view")
    public ResponseEntity<List<MemberWishPlaceDto>> getWishList(@RequestParam String memberId) {
        List<MemberWishPlaceDto> wishList = memberWishPlaceService.getWishList(memberId);
        if (wishList.isEmpty()) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.ok(wishList);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteWishPlace(@RequestParam String memberId, @RequestParam Long placeId) {
        boolean success = memberWishPlaceService.deleteWishPlace(memberId, placeId);
        if (success) {
            return ResponseEntity.ok("Wish place deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete wish place.");
        }
    }
}
