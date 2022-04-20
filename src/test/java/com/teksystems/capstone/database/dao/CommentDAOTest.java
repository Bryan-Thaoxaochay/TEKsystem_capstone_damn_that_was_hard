package com.teksystems.capstone.database.dao;

import com.teksystems.capstone.database.entity.Comment;
import com.teksystems.capstone.database.entity.Post;
import com.teksystems.capstone.database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Objects;

@Slf4j
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private CommentDAO commentDAO;

    @BeforeAll
    public void setup() {
        // Creating User
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Doe");
        newUser.setEmail("john_doe@example.com");
        newUser.setPassword("12345678");
        newUser.setUsername("JD");
        userDAO.save(newUser);

        // Creating Post
        User user = userDAO.findByEmail("john_doe@example.com");

        Post newPost = new Post();
        newPost.setUserId(user.getUserId());
        newPost.setTopic("Career");
        newPost.setTitle(": I can't find a job!");
        newPost.setDescription("Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit, sed do eiusmod tempor incididunt ut ");
        postDAO.save(newPost);

        // Creating Comment
        List<Post> post = postDAO.findByUserId(user.getUserId());
        for (int i = 0; i < 5; i++) {
            Comment newComment = new Comment();
            newComment.setComment(i + ": This story was amazing!");
            newComment.setBlogpostId(post.get(0).getUserId());
            newComment.setUserId(user.getUserId());
            commentDAO.save(newComment);
        }
    }

    @Test
    @Order(1)
    public void getAllCommentsByBlogpostId() {
        Assertions.assertTrue(commentDAO.getAllByBlogpostId(1).size() > 0);
    }

    @Test
    @Order(2)
    public void findCommentById() {
        log.info("----------------------" + commentDAO.findById(1).getComment());
        Assertions.assertEquals("0: This story was amazing!", commentDAO.findById(1).getComment());
    }
}
