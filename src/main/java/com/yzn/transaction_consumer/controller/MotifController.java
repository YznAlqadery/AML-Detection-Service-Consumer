package com.yzn.transaction_consumer.controller;


import com.yzn.transaction_consumer.model.Motif;
import com.yzn.transaction_consumer.service.MotifService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motifs")
public class MotifController {

    private final MotifService motifService;

    public MotifController(MotifService motifService) {
        this.motifService = motifService;
    }

    @GetMapping("")
    public List<Motif> getAllMotifs(){
        return motifService.getAllMotifs();
    }

    @GetMapping("/{id}")
    public Motif getMotifById(@PathVariable Integer id){
        return motifService.getMotifById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMotif(@PathVariable Integer id){
        motifService.deleteMotif(id);
    }

    @PostMapping("")
    public Motif createMotif(@RequestBody Motif motif){
        return motifService.createMotif(motif);
    }

    @PutMapping("/{id}")
    public Motif updateMotif(@PathVariable Integer id, @RequestBody Motif motif){
        return motifService.updateMotif(id,motif);
    }

}
