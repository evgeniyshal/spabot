package com.worldofmassage.spabot.repository;

import com.worldofmassage.spabot.entity.Role;
import com.worldofmassage.spabot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query( "select u from User u inner join u.roles r where r.authority in :roles" )
    List<User> findBySpecificRoles(@Param("roles") String... roles);
    User findByUsername(String username);
    User findByRolesContaining(Role role);
}