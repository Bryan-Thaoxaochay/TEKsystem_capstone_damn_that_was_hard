package com.teksystems.capstone.database.dao;

import com.teksystems.capstone.database.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostDAO extends JpaRepository<Post, Long> {
    Post findById(@Param("id") Integer id);
    List<Post> findByUserId(@Param("userId") Integer userId);
}
