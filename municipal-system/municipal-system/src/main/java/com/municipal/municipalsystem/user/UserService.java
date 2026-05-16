package com.municipal.municipalsystem.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {

        // Check if username already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setArea(request.getArea());
        user.setEnabled(true);

        // ===== ROLE ASSIGNMENT =====
        String roleName;

        if ("BUSINESS".equalsIgnoreCase(request.getRole())) {
            roleName = "ROLE_BUSINESS";

            user.setBusinessName(request.getBusinessName());
            user.setBusinessLicenseNumber(request.getBusinessLicenseNumber());
            user.setBusinessCategory(request.getBusinessCategory());

        } else {
            roleName = "ROLE_CITIZEN";
        }

        Role role = roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(new Role(roleName)));

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User updateUserRoles(Long userId, Set<String> roleNames) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Role> roles = new HashSet<>();

        for (String roleName : roleNames) {

            String formattedRole = roleName.startsWith("ROLE_")
                    ? roleName
                    : "ROLE_" + roleName.toUpperCase();

            Role role = roleRepository.findByName(formattedRole)
                    .orElseGet(() -> roleRepository.save(new Role(formattedRole)));

            roles.add(role);
        }

        user.setRoles(roles);

        return userRepository.save(user);
    }
// ================= ADMIN FEATURES =================

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void toggleUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
