package com.example.TravelPlanner.controller;

import com.example.TravelPlanner.dto.travelplan.ViewMyTravelPlanDto;
import com.example.TravelPlanner.service.travelplan.ViewMyTravelPlanServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TravelPlanController {

    private final ViewMyTravelPlanServiceImpl viewMyTravelPlanService;

    public TravelPlanController(ViewMyTravelPlanServiceImpl viewMyTravelPlanService) {
        this.viewMyTravelPlanService = viewMyTravelPlanService;
    }

    @GetMapping("/member/travelplans")
    public ResponseEntity<Optional<List<ViewMyTravelPlanDto>>> findAllByMemberId(@RequestParam String memberId) {
        Optional<List<ViewMyTravelPlanDto>> memberTravelPlanDtos = viewMyTravelPlanService.findAllByMemberId(memberId);
        if (memberTravelPlanDtos.isEmpty()) {
            System.out.println("No travel plans found");
            return ResponseEntity.ok().body(null);
        } else {
            System.out.println("Travel plans found");
            return ResponseEntity.ok(memberTravelPlanDtos);
        }
    }

}
