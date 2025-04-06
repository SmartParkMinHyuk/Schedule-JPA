package org.example.schedulespringdatajpa.repository;

import org.example.schedulespringdatajpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
