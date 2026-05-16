package com.municipal.municipalsystem.user;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;

import com.municipal.municipalsystem.dto.LoginRequest;
import com.municipal.municipalsystem.dto.LoginResponse;
import com.municipal.municipalsystem.security.JwtService;

import java.util.List;
import java.util.stream.Collectors;
import com.municipal.municipalsystem.user.User;
import com.municipal.municipalsystem.user.Role;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Fetch full user
        User user = userService.findByUsername(request.getUsername());

        List<String> roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        String token = jwtService.generateToken(user.getUsername(), roles);

        return new LoginResponse(token);
    }




}
