package com.yzn.transaction_consumer.controller;

import com.yzn.transaction_consumer.model.User;
import com.yzn.transaction_consumer.model.enums.Role;
import com.yzn.transaction_consumer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }


//    @GetMapping("")
//    public ResponseEntity<List<User>> getUsersByRole(@RequestParam Role role){
//        List<User> users = userService.getUsersByRole(role);
//
//        return ResponseEntity.ok(users);
//    }
}
