package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{
    
    @Query(name="get_permission_by_user",
        value = "select p.*\n" + //
                        "from perm_app p\n" + //
                        "inner join role_permission rp on rp.perm_id = p.id\n" + //
                        "inner join role_app r on r.id = rp.role_id\n" + //
                        "inner join user_role ur on ur.role_id = r.id\n" + //
                        "where ur.user_id = :id;",
        nativeQuery = true
    )
    public List<Permission> getUserPermissions(Long id);
}
