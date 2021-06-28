package com.worldofmassage.spabot.repository;

import com.worldofmassage.spabot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Set<Role> findByAuthorityIn(List<String> authorities);
    boolean existsByAuthority(String authority);
}
