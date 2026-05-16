package com.municipal.municipalsystem.garbage.service;

import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.List;

import com.municipal.municipalsystem.garbage.*;
import com.municipal.municipalsystem.garbage.repository.*;
import com.municipal.municipalsystem.user.*;

@Service
public class GarbageService {

    private final GarbageTypeRepository typeRepo;
    private final GarbageVehicleRepository vehicleRepo;
    private final GarbageScheduleRepository scheduleRepo;
    private final GarbageCollectionLogRepository logRepo;
    private final GarbageFeeRepository feeRepo;

    private final UserRepository userRepo;

    public GarbageService(GarbageTypeRepository typeRepo,
                          GarbageVehicleRepository vehicleRepo,
                          GarbageScheduleRepository scheduleRepo,
                          GarbageCollectionLogRepository logRepo,
                          GarbageFeeRepository feeRepo,
                          UserRepository userRepo) {

        this.typeRepo = typeRepo;
        this.vehicleRepo = vehicleRepo;
        this.scheduleRepo = scheduleRepo;
        this.logRepo = logRepo;
        this.feeRepo = feeRepo;
        this.userRepo = userRepo;
    }

    // ================= FEES =================

    public GarbageFee assignFee(GarbageFee fee) {

        // get user
        User user = userRepo.findById(fee.getCitizen().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        double weeklyKg = fee.getWeeklyKg();

        // calculate yearly garbage
        double yearlyKg = weeklyKg * 52;

        // determine rate
        double rate;

        boolean isBusiness = user.getRoles().stream()
                .anyMatch(r -> r.getName().equals("ROLE_BUSINESS"));

        if (isBusiness) {
            rate = 15; // business rate
        } else {
            rate = 8; // citizen rate
        }

        // calculate amount
        double amount = yearlyKg * rate;

        // set calculated values
        fee.setCitizen(user);
        fee.setYearlyKg(yearlyKg);
        fee.setRatePerKg(rate);
        fee.setAmount(amount);

        fee.setStatus("PENDING");

        return feeRepo.save(fee);
    }

    public List<GarbageFee> getAllFees() {

        return feeRepo.findAll();
    }

    // 🔐 JWT BASED ACCESS
    public List<GarbageFee> getMyFees(Authentication authentication) {

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return feeRepo.findByCitizenId(user.getId());
    }

    public GarbageFee markPayment(Long id, String method) {

        GarbageFee fee = feeRepo.findById(id).orElseThrow();

        fee.setStatus("PAID");
        fee.setPaymentDate(java.time.LocalDate.now());
        fee.setPaymentMethod(method);

        return feeRepo.save(fee);
    }

    // ================= GARBAGE TYPES =================

    public GarbageType addGarbageType(GarbageType type) {
        return typeRepo.save(type);
    }

    public List<GarbageType> getAllTypes() {
        return typeRepo.findAll();
    }

    // ================= VEHICLES =================

    public GarbageVehicle addVehicle(GarbageVehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    public List<GarbageVehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    // ================= SCHEDULE =================

    public GarbageSchedule addSchedule(GarbageSchedule schedule) {
        return scheduleRepo.save(schedule);
    }

    public List<GarbageSchedule> getSchedules() {
        return scheduleRepo.findAll();
    }

    // 🗺️ WARD BASED SCHEDULE
    public List<GarbageSchedule> getMySchedule(Authentication authentication){

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return scheduleRepo.findByArea(user.getArea());
    }

    // ================= COLLECTION LOG =================

    public GarbageCollectionLog addCollectionLog(GarbageCollectionLog log) {
        return logRepo.save(log);
    }

    public List<GarbageCollectionLog> getCollectionLogs() {
        return logRepo.findAll();
    }
}