package com.yzn.transaction_consumer.controller;


import com.yzn.transaction_consumer.model.enums.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @GetMapping("")
    public List<Role> getAllRoles(){
        return Arrays.asList(Role.values());
    }
}
