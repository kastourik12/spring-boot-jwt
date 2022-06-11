package com.example.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "app_users")
@Data @AllArgsConstructor @NoArgsConstructor
public class AppUser {

    @Column(name = "_id") @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(name = "_username") @NotBlank(message = "Username is required") @Min(value = 4, message = "Username must be at least 4 characters long")
    private String username;
    @Column(name = "_password") @NotBlank(message = "Password is required") @Min(value = 8, message = "Password must be at least 8 characters long")
    private String password;
    @Column(name = "_email") @Email(message = "Email is not valid") @NotBlank(message = "Email is required")
    private String email;
    @Column(name = "_firstName") @NotBlank(message = "First name is required")
    private String firstName;
    @Column(name = "_lastName") @NotBlank(message = "Last name is required")
    private String lastName;
    @Column(name = "_phone") @NotBlank(message = "Phone is required")
    private String phone;
    @Column(name = "_enabled") @NotNull
    private Boolean enabled;
    @Column(name = "roles") @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();
}
