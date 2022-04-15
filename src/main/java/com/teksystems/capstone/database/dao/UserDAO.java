package com.teksystems.capstone.database.dao;

import com.teksystems.capstone.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findByUsername(@Param("username") String username);
    User findByEmail(@Param("email") String email);
}
