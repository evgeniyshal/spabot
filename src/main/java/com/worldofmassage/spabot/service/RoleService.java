package com.worldofmassage.spabot.service;

import com.worldofmassage.spabot.entity.Role;
import com.worldofmassage.spabot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public List<Role> findByAuthorityIn(String... authorities) {
        return roleRepository.findByAuthorityIn(Arrays.asList(authorities));
    }

    public boolean roleExists(String authority) {
        return roleRepository.existsByAuthority(authority);
    }

    public void add(Role role) {
        roleRepository.save(role);
    }
}
