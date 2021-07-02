package com.worldofmassage.spabot.config;

import com.worldofmassage.spabot.entity.Role;
import com.worldofmassage.spabot.entity.User;
import com.worldofmassage.spabot.service.RoleService;
import com.worldofmassage.spabot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InitDB {

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
            addAdmin();
        }
    }

    private boolean adminRoleExists() {
        return roleService.roleExists("ROLE_ADMIN");
    }

    private void addAdminRole() {
        Role role = new Role();
        role.setAuthority("ROLE_ADMIN");
        roleService.add(role);
    }

    private boolean userRoleExists() {
        return roleService.roleExists("ROLE_USER");
    }

    private void addUserRole() {
        Role role = new Role();
        role.setAuthority("ROLE_USER");
        roleService.add(role);
    }

    private boolean adminExists() {
        return !userService.findBySpecificRoles("ROLE_ADMIN").isEmpty();
    }

    private void addAdmin() {
        User defaultUser = new User();
        defaultUser.setUsername("admin");
        defaultUser.setPassword("admin");
        defaultUser.setFirstName("Admin");
        defaultUser.setLastName("Admin");
        userService.save(defaultUser, "ROLE_ADMIN");
    }
}
