package com.municipal.municipalsystem.garbage;

import jakarta.persistence.*;
import java.time.DayOfWeek;

@Entity
@Table(name = "garbage_schedules")
public class GarbageSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Area / Zone of the city
    @Column(nullable = false)
    private String area;

    // Day of garbage collection
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek collectionDay;

    // Vehicle assigned for collection
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private GarbageVehicle vehicle;

    public GarbageSchedule() {
    }

    public GarbageSchedule(String area, DayOfWeek collectionDay, GarbageVehicle vehicle) {
        this.area = area;
        this.collectionDay = collectionDay;
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public DayOfWeek getCollectionDay() {
        return collectionDay;
    }

    public GarbageVehicle getVehicle() {
        return vehicle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCollectionDay(DayOfWeek collectionDay) {
        this.collectionDay = collectionDay;
    }

    public void setVehicle(GarbageVehicle vehicle) {
        this.vehicle = vehicle;
    }
}