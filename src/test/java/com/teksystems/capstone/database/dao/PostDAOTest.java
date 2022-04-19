package com.teksystems.capstone.database.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostDAOTest {
    @Autowired
    private PostDAO postDAO;

    @Test
    @Order(1)
    public void createPostTest() {

    }

    @Test
    @Order(2)
    public void getPostTest() {

    }

    @Test
    @Order(3)
    public void updatePostTest() {

    }

    @Test
    @Order(4)
    public void deletePostTest() {

    }
}

/*
1. Add h2 db dependency to the pom.xml
2. Create application.properties in resource folder in test folder
3. Add these properties
    hibernate.dialect=org.hibernate.dialect.H2Dialect
    hibernate.hbm2ddl.auto=create
4. Create test classes
5. Add annotations
    @DataJpaTest
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
*/