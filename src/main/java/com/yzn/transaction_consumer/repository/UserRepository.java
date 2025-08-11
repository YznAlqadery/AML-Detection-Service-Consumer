package com.yzn.transaction_consumer.repository;

import com.yzn.transaction_consumer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUsername(String username);
}
