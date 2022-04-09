package com.teksystems.capstone.database.dao;

import com.teksystems.capstone.database.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Long> {

}
