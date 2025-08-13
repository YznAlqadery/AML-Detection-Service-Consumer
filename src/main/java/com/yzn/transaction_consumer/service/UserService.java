package com.yzn.transaction_consumer.service;


import com.yzn.transaction_consumer.model.User;
import com.yzn.transaction_consumer.model.enums.Role;
import com.yzn.transaction_consumer.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public List<User> getUsersByRole(Role role){
        return userRepository.findByRole(role);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found."));

        userRepository.delete(user);
    }
}
