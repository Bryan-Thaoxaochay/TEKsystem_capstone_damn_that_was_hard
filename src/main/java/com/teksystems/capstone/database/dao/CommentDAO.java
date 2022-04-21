package com.teksystems.capstone.database.dao;

import com.teksystems.capstone.database.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Long> {
    List<Comment> getAllByBlogpostIdOrderByUpdateDateDesc(@Param("id") Integer blogpostId);
    Comment findById(@Param("commentId") Integer commentId);
}
