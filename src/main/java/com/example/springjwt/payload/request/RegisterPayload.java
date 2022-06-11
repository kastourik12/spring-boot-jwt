package com.example.springjwt.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor @NoArgsConstructor
public class RegisterPayload {
    @NotBlank(message = "Username is required")
    @Min(value = 4, message = "Username must be at least 4 characters long")
    private String username;
    @NotBlank(message = "Password is required") @Min(value = 8, message = "Password must be at least 8 characters long")
    private String password;
    @Email(message = "Email is not valid") @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Phone is required")
    private String phone;
    private String address;
    private String city;
    private String country;
    private String zip;
    @Nullable
    private String[] roles;
}
