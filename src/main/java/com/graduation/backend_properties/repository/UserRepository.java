package com.graduation.backend_properties.repository;

import com.graduation.backend_properties.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
   Optional<User> findByEmail(String email);
}
