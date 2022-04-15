package com.teksystems.capstone.database.dao;

import com.teksystems.capstone.database.entity.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserPostDAO extends JpaRepository<UserPost, Long> {
    List<UserPost> getAllByUserId(@Param("userId") Integer userId);
    UserPost findUserPostByUserIdAndPostId(@Param("userId") Integer userId, @Param("postId") Integer postId);
}
