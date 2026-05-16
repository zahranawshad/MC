package com.municipal.municipalsystem.garbage.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

import com.municipal.municipalsystem.garbage.GarbageFee;
import com.municipal.municipalsystem.garbage.GarbageSchedule;
import com.municipal.municipalsystem.garbage.service.GarbageService;

@RestController
@RequestMapping("/api/citizen/garbage")
public class CitizenGarbageController {

    private final GarbageService garbageService;

    public CitizenGarbageController(GarbageService garbageService) {
        this.garbageService = garbageService;
    }

    // 🗺️ GET SCHEDULE FOR LOGGED USER
    @GetMapping("/my-schedule")
    public List<GarbageSchedule> getMySchedule(Authentication authentication) {

        return garbageService.getMySchedule(authentication);
    }

    // 🔐 GET FEES FOR LOGGED USER
    @GetMapping("/my-fees")
    public List<GarbageFee> getMyFees(Authentication authentication) {

        return garbageService.getMyFees(authentication);
    }
}