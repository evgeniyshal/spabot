package com.worldofmassage.spabot.repository;

import com.worldofmassage.spabot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
