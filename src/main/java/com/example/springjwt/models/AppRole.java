package com.example.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAttribute;

@Entity(name = "app_roles")
@Data @AllArgsConstructor @NoArgsConstructor
public class AppRole {
    @Column(name = "id") @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column( name = "name" ) @NotBlank(message = "Name is required")
    private String roleName;


}
