package com.yzn.transaction_consumer.controller;

import com.yzn.transaction_consumer.model.User;
import com.yzn.transaction_consumer.model.dto.RegisterRequest;
import com.yzn.transaction_consumer.model.dto.UserDTO;
import com.yzn.transaction_consumer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());

        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }

    @PutMapping("/update-user/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO){
        userService.updateUser(id,userDTO);
    }

}
