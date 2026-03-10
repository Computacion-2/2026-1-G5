package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class UserRolePK {
    
    @Column(name = "role_id", insertable = false, updatable = false)
    private Long roleId;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;
}
