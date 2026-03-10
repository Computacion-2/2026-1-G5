package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "perm_app")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_perm")
    @SequenceGenerator(name = "seq_perm", initialValue = 6, allocationSize = 1)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private List<Role> roles;

}
