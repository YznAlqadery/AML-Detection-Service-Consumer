package com.yzn.transaction_consumer.controller;


import com.yzn.transaction_consumer.model.User;
import com.yzn.transaction_consumer.model.dto.LoginRequest;
import com.yzn.transaction_consumer.model.dto.LoginResponse;
import com.yzn.transaction_consumer.model.dto.RegisterRequest;
import com.yzn.transaction_consumer.model.enums.Role;
import com.yzn.transaction_consumer.security.CustomUserDetails;
import com.yzn.transaction_consumer.security.JwtUtils;
import com.yzn.transaction_consumer.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest loginRequest
            ){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Set authentication in context
        SecurityContextHolder.getContext().setAuthentication(authentication);



        // Get user details (custom)
        CustomUserDetails customUserDetails =(CustomUserDetails) authentication.getPrincipal();

        // Generate JWT Token
        String jwt = jwtUtils.generateTokenFromUsername(customUserDetails);

        String roleString = customUserDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        Role role = Role.valueOf(roleString);


        // Build Response with username, token and role
        LoginResponse loginResponse = new LoginResponse(
                customUserDetails.getUsername(),
                jwt,
                role
        );
    return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        if(userService.userExists(request.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body("Username is already taken.");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);

        System.out.println("Registering user role: " + user.getRole());  // should print USER


        userService.saveUser(user);
        return ResponseEntity.ok("User Registered Successfully");

    }
}
