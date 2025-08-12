    package com.yzn.transaction_consumer.repository;

    import com.yzn.transaction_consumer.model.User;
    import com.yzn.transaction_consumer.model.enums.Role;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.List;
    import java.util.Optional;

    @Repository
    public interface UserRepository extends JpaRepository<User,Integer> {
        Optional<User> findByUsername(String username);
        List<User> findByRole(Role role);
    }
