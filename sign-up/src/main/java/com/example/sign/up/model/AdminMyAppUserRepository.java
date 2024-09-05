package com.example.sign.up.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminMyAppUserRepository extends JpaRepository<AdminMyAppUser, Long> {
    
    Optional<AdminMyAppUser> findByUserName(String userName);
}
