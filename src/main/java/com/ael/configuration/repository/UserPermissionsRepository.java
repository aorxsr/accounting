package com.ael.configuration.repository;

import com.ael.configuration.entity.UserPermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPermissionsRepository extends JpaRepository<UserPermissions, String> {

    List<UserPermissions> findAllByUserId(String userId);

}
