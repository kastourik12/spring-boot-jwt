package com.example.springjwt.services;

import com.example.springjwt.models.AppRole;
import com.example.springjwt.models.AppUser;
import com.example.springjwt.payload.request.RegisterPayload;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(RegisterPayload registerPayload);
    AppRole addNewRole(AppRole role);
    void addRoleToUser(String username, String roleName);

    List<AppUser> findAllUsers();
}
