package com.example.springjwt.repositories;

import com.example.springjwt.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}
