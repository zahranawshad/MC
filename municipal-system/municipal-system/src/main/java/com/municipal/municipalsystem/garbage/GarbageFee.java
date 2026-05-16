package com.municipal.municipalsystem.garbage;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.municipal.municipalsystem.user.User;

@Entity
@Table(name = "garbage_fees")
public class GarbageFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double weeklyKg;
    private double yearlyKg;
    private double ratePerKg;
    private double amount;

    private LocalDate dueDate;

    private String status;

    private LocalDate paymentDate;

    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User citizen;

    public GarbageFee() {}

    public Long getId() { return id; }

    public double getWeeklyKg() { return weeklyKg; }

    public double getYearlyKg() { return yearlyKg; }

    public double getRatePerKg() { return ratePerKg; }

    public double getAmount() { return amount; }

    public LocalDate getDueDate() { return dueDate; }

    public String getStatus() { return status; }

    public LocalDate getPaymentDate() { return paymentDate; }

    public String getPaymentMethod() { return paymentMethod; }

    public User getCitizen() { return citizen; }

    public void setId(Long id) { this.id = id; }

    public void setWeeklyKg(double weeklyKg) { this.weeklyKg = weeklyKg; }

    public void setYearlyKg(double yearlyKg) { this.yearlyKg = yearlyKg; }

    public void setRatePerKg(double ratePerKg) { this.ratePerKg = ratePerKg; }

    public void setAmount(double amount) { this.amount = amount; }

    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public void setStatus(String status) { this.status = status; }

    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public void setCitizen(User citizen) { this.citizen = citizen; }

}