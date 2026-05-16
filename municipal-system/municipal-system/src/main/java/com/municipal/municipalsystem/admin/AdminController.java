package com.municipal.municipalsystem.admin;

import com.municipal.municipalsystem.user.User;
import com.municipal.municipalsystem.user.UserService;
import com.municipal.municipalsystem.user.RegisterRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/users/{id}/toggle")
    public void toggleUser(@PathVariable Long id) {
        userService.toggleUser(id);
    }

    @PutMapping("/users/{id}/roles")
    public User updateUserRoles(
            @PathVariable Long id,
            @RequestBody Set<String> roles) {

        return userService.updateUserRoles(id, roles);
    }
}
