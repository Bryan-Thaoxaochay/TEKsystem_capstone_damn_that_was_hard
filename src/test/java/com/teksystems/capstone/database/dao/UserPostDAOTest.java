package com.teksystems.capstone.database.dao;

import com.teksystems.capstone.database.entity.Post;
import com.teksystems.capstone.database.entity.User;
import com.teksystems.capstone.database.entity.UserPost;
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
public class UserPostDAOTest {
    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserPostDAO userPostDAO;

    @BeforeAll
    public void setup() {
        User userOne = new User();
        userOne.setFirstName("John");
        userOne.setLastName("Doe");
        userOne.setEmail("john_doe@example.com");
        userOne.setPassword("12345678");
        userOne.setUsername("John");

        User userTwo = new User();
        userTwo.setFirstName("Jane");
        userTwo.setLastName("Doe");
        userTwo.setEmail("jane_doe@example.com");
        userTwo.setPassword("12345678");
        userTwo.setUsername("Jane");

        userDAO.save(userOne);
        userDAO.save(userTwo);

        User john = userDAO.findByEmail("john_doe@example.com");

        for (int i = 1; i < 5; i++) {
            Post newPost = new Post();
            newPost.setUserId(john.getUserId());
            newPost.setTopic("Career");
            newPost.setTitle(i + ": I can't find a job!");
            newPost.setDescription("Lorem ipsum dolor sit amet, consectetur " +
                    "adipiscing elit, sed do eiusmod tempor incididunt ut ");
            postDAO.save(newPost);
        }

        User jane = userDAO.findByEmail("jane_doe@example.com");
        List<Post> posts = postDAO.getAllPostsOrderByCreateDate();
        for (Post post : posts) {
            UserPost savedPost = new UserPost();
            savedPost.setPostId(post.getId());
            savedPost.setUserId(jane.getUserId());
            userPostDAO.save(savedPost);
        }
    }

    @Test
    @Order(1)
    public void getAllSavedPostsByUserId() {
        log.info("-----------------First Name: " + userDAO.findByEmail("jane_doe@example.com").getFirstName());
        Assertions.assertTrue(userPostDAO.getAllByUserId(2).size() > 0);
    }

    @Test
    @Order(2)
    public void findUserPostByUserIdAndPostId() {
        Integer userId = userDAO.findByEmail("jane_doe@example.com").getUserId();
        log.info("--------------Post: " + postDAO.findById(1).getTitle());

        Assertions.assertNotNull(userPostDAO.findUserPostByUserIdAndPostId(userId, 1));
    }

    // CRUD Tests
    @Test
    @Order(3)
    public void createUserPost() {
        User userCreatingPost = userDAO.findByEmail("john_doe@example.com");
        User userSavingPost = userDAO.findByEmail("jane_doe@example.com");

        Post newPost = new Post();
            newPost.setUserId(userCreatingPost.getUserId());
            newPost.setTopic("Education");
            newPost.setTitle("School is life");
            newPost.setDescription("Lorem ipsum dolor sit amet, consectetur " +
                    "adipiscing elit, sed do eiusmod tempor incididunt ut ");
            postDAO.save(newPost);

        List<Post> posts = postDAO.getAllPostsOrderByCreateDate();
        Post post = postDAO.findById(posts.size() - 1);
        log.info("------------------------" + (Objects.equals(post.getTitle(), "School is life")));

        int userPostLength = userPostDAO.findAll().size();

        UserPost savedPost = new UserPost();
            savedPost.setPostId(post.getId());
            savedPost.setUserId(userSavingPost.getUserId());
            userPostDAO.save(savedPost);

        int updatedUserPostLength = userPostDAO.findAll().size();

        Assertions.assertTrue(updatedUserPostLength > userPostLength);
    }

    @Test
    @Order(4)
    public void readUserPost() {
        int userId = userDAO.findByUserId(2).getUserId();
        int postId = postDAO.findById(1).getId();

        log.info("--------------------- " + userId);
        log.info("--------------------- " + postId);

        UserPost userPost = userPostDAO.findUserPostByUserIdAndPostId(userId, postId);

        log.info("--------------------- " + userPost.toString());
        Assertions.assertNotNull(userPost);
    }

    @Test
    @Order(6)
    public void deleteUserPost() {
        UserPost userPost = userPostDAO.findUserPostByUserIdAndPostId(2, 1);
        userPostDAO.delete(userPost);

        Assertions.assertNull(userPostDAO.findUserPostByUserIdAndPostId(2, 1));
    }
}
