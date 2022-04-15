package com.teksystems.capstone.database.dao;

import com.teksystems.capstone.database.entity.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostDAO extends JpaRepository<UserPost, Long> {

}
