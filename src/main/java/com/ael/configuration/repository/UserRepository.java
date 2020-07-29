package com.ael.configuration.repository;

import com.ael.configuration.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    public Users findTopByUsername(String username);

}
