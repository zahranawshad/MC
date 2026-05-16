package com.municipal.municipalsystem.garbage;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "garbage_collection_logs")
public class GarbageCollectionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String area;

    private LocalDate collectionDate;

    private double collectedWeight;

    private String remarks;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private GarbageVehicle vehicle;

    public GarbageCollectionLog() {}

    public Long getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public double getCollectedWeight() {
        return collectedWeight;
    }

    public String getRemarks() {
        return remarks;
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

    public void setCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
    }

    public void setCollectedWeight(double collectedWeight) {
        this.collectedWeight = collectedWeight;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setVehicle(GarbageVehicle vehicle) {
        this.vehicle = vehicle;
    }
}