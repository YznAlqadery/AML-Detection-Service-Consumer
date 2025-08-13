package com.yzn.transaction_consumer.controller;


import com.yzn.transaction_consumer.service.TransactionService;
import org.neo4j.driver.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/fraud-cycles/{id}")
    public ResponseEntity<?> getFraudCycles(@PathVariable Integer id) {
        try {
            var response = transactionService.getFraudCycles(id);

            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error retrieving fraud cycles.");
        }
    }

}

