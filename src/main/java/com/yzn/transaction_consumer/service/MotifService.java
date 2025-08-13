package com.yzn.transaction_consumer.service;


import com.yzn.transaction_consumer.model.Motif;
import com.yzn.transaction_consumer.repository.MotifRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotifService {

    private final MotifRepository motifRepository;

    public MotifService(MotifRepository motifRepository) {
        this.motifRepository = motifRepository;
    }

    public List<Motif> getAllMotifs(){
        return motifRepository.findAll();
    }

    public Motif getMotifById(Integer id){
        return motifRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Motif with " + id + " found"));
    }

    public Motif createMotif(Motif motif){
        return motifRepository.save(motif);
    }

    public Motif updateMotif(Integer id, Motif updatedMotif){
        Motif motif = motifRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Motif with " + id + " found."));

        motif.setName(updatedMotif.getName()!=null ? updatedMotif.getName(): motif.getName());
        motif.setDescription(updatedMotif.getDescription()!=null? updatedMotif.getDescription(): motif.getDescription());
        motif.setCypherQuery(updatedMotif.getCypherQuery()!=null? updatedMotif.getCypherQuery(): motif.getCypherQuery());
        motif.setActive(updatedMotif.isActive()!=null? updatedMotif.isActive(): motif.isActive());

        return motifRepository.save(motif);
    }

    public void deleteMotif(Integer id){
        motifRepository.deleteById(id);
    }
}
