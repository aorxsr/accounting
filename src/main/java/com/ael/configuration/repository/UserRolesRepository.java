package com.ael.configuration.repository;

import com.ael.configuration.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles, String> {
    List<UserRoles> findAllByUserId(String userId);
}
