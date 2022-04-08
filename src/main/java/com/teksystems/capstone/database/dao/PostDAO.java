package com.teksystems.capstone.database.dao;

import com.teksystems.capstone.database.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO extends JpaRepository<Post, Long> {
    public Post findById(@Param("id") Integer id);
}
