package com.yzn.transaction_consumer.controller;

import com.yzn.transaction_consumer.model.User;
import com.yzn.transaction_consumer.model.dto.RegisterRequest;
import com.yzn.transaction_consumer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest request){
        if(userService.userExists(request.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body("Username is already taken.");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        //  System.out.println("Registering user role: " + user.getRole());  // should print USER


        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }


}
