package com.example.TravelPlanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Model model) {
        return "main";
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "main_dashboard";
    }
    @GetMapping("/map")
    public String findPlace(Model model) {
        return "map";
    }
    @GetMapping("/favourites")
    public String favourites(Model model) {
        return "favourites";
    }
    @GetMapping("/calendar")
    public String calendar(Model model) {
        return "calendar";
    }
}
