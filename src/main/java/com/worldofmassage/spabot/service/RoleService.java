package com.worldofmassage.spabot.service;

import com.worldofmassage.spabot.entity.Role;
import com.worldofmassage.spabot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public boolean roleExists(String authority) {
        return roleRepository.existsByAuthority(authority);
    }

    public void add(Role role) {
        roleRepository.save(role);
    }
}
