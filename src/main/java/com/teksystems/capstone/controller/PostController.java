package com.teksystems.capstone.controller;

import com.teksystems.capstone.bean.PostBean;
import com.teksystems.capstone.database.dao.CommentDAO;
import com.teksystems.capstone.database.dao.PostDAO;
import com.teksystems.capstone.database.dao.UserDAO;
import com.teksystems.capstone.database.entity.Comment;
import com.teksystems.capstone.database.entity.Post;
import com.teksystems.capstone.database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Slf4j
@Controller
public class PostController {
    @Autowired
    private PostDAO postDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private UserDAO userDAO;

    // Getting Post(s) and Comments
    @GetMapping("/posts")
    public ModelAndView getAllPosts() {
        ModelAndView response = new ModelAndView();
        response.setViewName("post/posts");

        List<Post> posts = postDAO.findAll();
        response.addObject("posts", posts);

        return response;
    }

    @GetMapping("/posts/my_posts")
    public ModelAndView getUserPosts() {
        ModelAndView response = new ModelAndView();
        response.setViewName("post/posts");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User user = userDAO.findByEmail(userEmail);

        List<Post> userPosts = postDAO.findByUserId(user.getUserId());
        response.addObject("posts", userPosts);

        return response;
    }

    @GetMapping("/posts/post/{postId}")
    public ModelAndView getPostAndComments(@PathVariable("postId") Integer postId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("post/post");

        Post post = postDAO.findById(postId);
        response.addObject("post", post);

        List<Comment> comments = commentDAO.getAllByBlogpostId(postId);
        response.addObject("comments", comments);

        return response;
    }

    // Creating Post
    @GetMapping("/posts/create_post")
    public ModelAndView displayCreatePostPage() {
        ModelAndView response = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User user = userDAO.findByEmail(userEmail);

        response.addObject("user", user);
        response.setViewName("post/create_post");
        return response;
    }

    @PostMapping("/posts/create/{userId}")
    public ModelAndView createPost(@PathVariable("userId") Integer userId, PostBean form) {
        ModelAndView response = new ModelAndView();

        Post post = new Post();
        post.setTopic(form.getTopic());
        post.setTitle(form.getTitle());
        post.setDescription(form.getDescription());
        post.setUserId(userId);

        postDAO.save(post);

        response.setViewName("post/posts");
        return response;
    }

    // Editing Post
    @GetMapping("/posts/post/edit/{postId}")
    public ModelAndView displayEditPostPage(@PathVariable("postId") Integer postId) {
        ModelAndView response = new ModelAndView();
        response.setViewName("post/create_post");

        Post post = postDAO.findById(postId);
        response.addObject("post", post);

        return response;
    }

    @PostMapping("/post/edit/{postId}")
    public ModelAndView editPost(@PathVariable("postId") Integer postId, PostBean form) {
        ModelAndView response = new ModelAndView();

        Post post = postDAO.findById(postId);
        post.setId(form.getId());
        post.setTopic(form.getTopic());
        post.setTitle(form.getTitle());
        post.setDescription(form.getDescription());
        post.setUserId(form.getUserId());

        postDAO.save(post);

        response.setViewName("redirect:/posts/post/" + postId);
        return response;
    }

    // Deleting Post
    @PostMapping("/posts/delete/{postId}")
    public ModelAndView deletePost(@PathVariable("postId") Integer postId) {
        ModelAndView response = new ModelAndView();

        Post post = postDAO.findById(postId);
        postDAO.delete(post);

        response.setViewName("redirect:/posts");
        return response;
    }
}
