package com.worldofmassage.spabot.repository;

import com.worldofmassage.spabot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByAuthorityIn(List<String> authorities);
    boolean existsByAuthority(String authority);
}
