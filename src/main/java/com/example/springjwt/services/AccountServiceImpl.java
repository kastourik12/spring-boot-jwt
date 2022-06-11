package com.example.springjwt.services;

import com.example.springjwt.models.AppRole;
import com.example.springjwt.models.AppUser;
import com.example.springjwt.payload.request.RegisterPayload;
import com.example.springjwt.repositories.AppRoleRepository;
import com.example.springjwt.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = appUserRepository.findByUsername(username);
        AppRole role = appRoleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }
}
