package com.municipal.municipalsystem.garbage.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.municipal.municipalsystem.garbage.*;
import com.municipal.municipalsystem.garbage.service.GarbageService;

@RestController
@RequestMapping("/api/admin/garbage")
public class AdminGarbageController {

    private final GarbageService garbageService;

    public AdminGarbageController(GarbageService garbageService) {
        this.garbageService = garbageService;
    }

    @PostMapping("/types")
    public GarbageType addType(@RequestBody GarbageType type) {
        return garbageService.addGarbageType(type);
    }

    @GetMapping("/types")
    public List<GarbageType> getTypes() {
        return garbageService.getAllTypes();
    }

    @PostMapping("/vehicles")
    public GarbageVehicle addVehicle(@RequestBody GarbageVehicle vehicle) {
        return garbageService.addVehicle(vehicle);
    }

    @GetMapping("/vehicles")
    public List<GarbageVehicle> getVehicles() {
        return garbageService.getAllVehicles();
    }

    @PostMapping("/schedules")
    public GarbageSchedule addSchedule(@RequestBody GarbageSchedule schedule) {
        return garbageService.addSchedule(schedule);
    }

    @GetMapping("/schedules")
    public List<GarbageSchedule> getSchedules() {
        return garbageService.getSchedules();
    }

    @PostMapping("/logs")
    public GarbageCollectionLog addLog(@RequestBody GarbageCollectionLog log) {
        return garbageService.addCollectionLog(log);
    }

    @GetMapping("/logs")
    public List<GarbageCollectionLog> getLogs() {
        return garbageService.getCollectionLogs();
    }

    @PostMapping("/fees")
    public GarbageFee assignFee(@RequestBody GarbageFee fee) {

        return garbageService.assignFee(fee);
    }

    @GetMapping("/fees")
    public List<GarbageFee> getFees() {

        return garbageService.getAllFees();
    }
    @PutMapping("/fees/{id}/pay")
    public GarbageFee markPayment(@PathVariable Long id,
                                  @RequestParam String method) {

        return garbageService.markPayment(id, method);
    }
}