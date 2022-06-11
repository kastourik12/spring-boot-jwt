package com.example.springjwt.services;

import com.example.springjwt.models.AppRole;
import com.example.springjwt.models.AppUser;
import com.example.springjwt.payload.request.RegisterPayload;
import com.example.springjwt.repositories.AppRoleRepository;
import com.example.springjwt.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Override
    public AppUser addNewUser(RegisterPayload registerPayload) {
        AppUser appUser = new AppUser();
        appUser.setUsername(registerPayload.getUsername());
        appUser.setPassword(bCryptPasswordEncoder.encode(registerPayload.getPassword()));
        appUser.setEmail(registerPayload.getEmail());
        appUser.setFirstName(registerPayload.getFirstName());
        appUser.setLastName(registerPayload.getLastName());
        appUser.setPhone(registerPayload.getPhone());
        appUser.setEnabled(false);
        if(registerPayload.getRoles() != null) {
            AppRole defaultRole = appRoleRepository.findByRoleName("ROLE_USER").orElseThrow(() -> new UsernameNotFoundException("Role not found"));
            appUser.getRoles().add(defaultRole);
        }
        if (registerPayload.getRoles() != null) {
            for (String customRoles : registerPayload.getRoles()) {
                AppRole addedRole = appRoleRepository.findByRoleName(customRoles).orElseThrow(() -> new UsernameNotFoundException("Role not found"));
                appUser.getRoles().add(addedRole);
            }
        }
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = appUserRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User with username:" + username + " not found"));
        AppRole role = appRoleRepository.findByRoleName(roleName).orElseThrow(() -> new UsernameNotFoundException("Role with name:" + roleName + " not found"));
        user.getRoles().add(role);
    }
    @Override
    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }
}
