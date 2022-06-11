package com.example.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "app_users")
@Data @AllArgsConstructor @NoArgsConstructor
public class AppUser {

    @Column(name = "id") @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column( name = "username" ) @NotBlank(message = "Username is required") @Min(value = 4, message = "Username must be at least 4 characters long")
    private String username;
    @Column( name = "password" ) @NotBlank(message = "Password is required") @Min(value = 8, message = "Password must be at least 8 characters long")
    private String password;
    @Column( name = "email" ) @Email(message = "Email is not valid") @NotBlank(message = "Email is required")
    private String email;
    @Column(name = "first_name") @NotBlank(message = "First name is required")
    private String firstName;
    @Column(name = "last_name") @NotBlank(message = "Last name is required")
    private String lastName;
    @Column(name = "phone") @NotBlank(message = "Phone is required")
    private String phone;
    @Column(name = "enabled")
    private Boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();
}
