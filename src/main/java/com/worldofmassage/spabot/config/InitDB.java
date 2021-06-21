package com.worldofmassage.spabot.config;

import com.worldofmassage.spabot.entity.Role;
import com.worldofmassage.spabot.entity.User;
import com.worldofmassage.spabot.service.RoleService;
import com.worldofmassage.spabot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class InitDB {

    @PersistenceContext
    private EntityManager entityManager;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {
        if (!adminRoleExists()) {
            addAdminRole();
        }

        if (!userRoleExists()) {
            addUserRole();
        }

        if (!adminExists()) {
            addDefaultUser();
        }
    }

    private boolean adminExists() {
        return userService.findAll().stream()
                .anyMatch(user -> user.getAuthorities().stream()
                        .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN")));
    }

    private boolean adminRoleExists() {
        return roleService.findAll().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
    }

    private void addAdminRole() {
        Role role = new Role();
        role.setAuthority("ROLE_ADMIN");
        roleService.add(role);
    }

    private boolean userRoleExists() {
        return roleService.findAll().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_USER"));
    }

    private void addUserRole() {
        Role role = new Role();
        role.setAuthority("ROLE_USER");
        roleService.add(role);
    }

    private void addDefaultUser() {
        User defaultUser = new User();
        defaultUser.setUsername("admin");
        defaultUser.setPassword("admin");
        userService.add(defaultUser, "ROLE_ADMIN");
    }
}
