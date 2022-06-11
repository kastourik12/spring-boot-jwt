package com.example.springjwt.services;

import com.example.springjwt.models.AppRole;
import com.example.springjwt.models.AppUser;
import com.example.springjwt.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser customer = appUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Boolean enabled = customer.getEnabled();
        List<GrantedAuthority> authorities = customer.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
        UserDetails user = User.withUsername(username)
                .password(customer.getPassword())
                .authorities(authorities)
                .disabled(!enabled)
                .build();
        return user;
    }
}
