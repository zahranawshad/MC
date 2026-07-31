package com.municipal.municipalsystem.user;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterRequest {

    private String username;
    private String email;
    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
            message = "Password must contain at least 8 characters, one uppercase letter, one lowercase letter, one number and one special character."
    )
    private String password;
    private String role;   // CITIZEN or BUSINESS

    private String fullName;
    private String phone;
    private String address;
    private String area;

    // Business only fields
    private String businessName;
    private String businessLicenseNumber;
    private String businessCategory;

    // GETTERS & SETTERS

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getBusinessLicenseNumber() { return businessLicenseNumber; }
    public void setBusinessLicenseNumber(String businessLicenseNumber) { this.businessLicenseNumber = businessLicenseNumber; }

    public String getBusinessCategory() { return businessCategory; }
    public void setBusinessCategory(String businessCategory) { this.businessCategory = businessCategory; }
}