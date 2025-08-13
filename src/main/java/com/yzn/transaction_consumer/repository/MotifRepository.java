package com.yzn.transaction_consumer.repository;

import com.yzn.transaction_consumer.model.Motif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotifRepository extends JpaRepository<Motif,Integer> {
}
