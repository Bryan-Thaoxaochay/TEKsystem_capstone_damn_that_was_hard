package com.teksystems.capstone.database.dao;

import com.teksystems.capstone.database.entity.Post;
import com.teksystems.capstone.database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostDAOTest {
    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserDAO userDAO;

    @BeforeAll
    public void setup() throws ParseException {
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Doe");
        newUser.setEmail("john_doe@example.com");
        newUser.setPassword("12345678");
        newUser.setUsername("JD");
        userDAO.save(newUser);

        User user = userDAO.findByEmail("john_doe@example.com");

        for (int i = 1; i < 5; i++) {
            String date = "31/12/199" + i;
            Post newPost = new Post();
            newPost.setUserId(user.getUserId());
            newPost.setTopic("Career");
            newPost.setTitle(i + ": I can't find a job!");
            newPost.setDescription("Lorem ipsum dolor sit amet, consectetur " +
                    "adipiscing elit, sed do eiusmod tempor incididunt ut ");
            newPost.setCreateDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
            postDAO.save(newPost);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    @Order(1)
    public void findPostById(Integer id) {
        log.info("--------------------" + postDAO.findById(id).getTitle());
        Assertions.assertNotNull(postDAO.findById(id));
    }

    @Test
    @Order(2)
    public void findPostsByUserId() {
        Assertions.assertTrue(postDAO.findByUserId(1).size() > 0);
    }

    @Test
    @Order(3)
    public void getAllPostsOrderByCreateDate() {
        List<Post> posts = postDAO.getAllPostsOrderByCreateDate();
        Date laterDate = posts.get(0).getCreateDate();
        Date beforeDate = posts.get(posts.size() - 1).getCreateDate();

        log.info("---------------- Later Date: " + laterDate.toString());
        log.info("---------------- Before Date: " + beforeDate.toString());

        Assertions.assertTrue(beforeDate.compareTo(laterDate) < 0);
    }
}
