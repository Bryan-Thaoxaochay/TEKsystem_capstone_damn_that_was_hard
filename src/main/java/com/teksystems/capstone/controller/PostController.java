package com.teksystems.capstone.controller;

import com.teksystems.capstone.bean.PostBean;
import com.teksystems.capstone.database.dao.CommentDAO;
import com.teksystems.capstone.database.dao.PostDAO;
import com.teksystems.capstone.database.dao.UserDAO;
import com.teksystems.capstone.database.entity.Comment;
import com.teksystems.capstone.database.entity.Post;
import com.teksystems.capstone.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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
        List<Post> posts = postDAO.getAllPostsOrderByCreateDate();

        return new ModelAndView("post/posts").addObject("posts", posts);
    }

    @GetMapping("/posts/my_posts")
    public ModelAndView getUserPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userDAO.findByEmail(userEmail);

        List<Post> userPosts = postDAO.findByUserId(user.getUserId());

        return new ModelAndView("post/posts").addObject("posts", userPosts);
    }

    @GetMapping("/posts/post/{postId}")
    public ModelAndView getPostAndComments(@PathVariable("postId") Integer postId) throws Exception {
        Post post = postDAO.findById(postId);
        List<Comment> comments = commentDAO.getAllByBlogpostId(postId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User currentUser = userDAO.findByEmail(userEmail);

        User postUser = userDAO.findByUserId(post.getUserId());

        return new ModelAndView("post/post")
                .addObject("post", post).addObject("comments", comments)
                .addObject("currentUser", currentUser).addObject("postUser", postUser);
    }

    // Creating Post
    @GetMapping("/posts/create_post")
    public ModelAndView displayCreatePostPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userDAO.findByEmail(userEmail);

        return new ModelAndView("post/create_post").addObject("user", user);
    }

    @PostMapping("/posts/create/{userId}")
    public ModelAndView createPost(@PathVariable("userId") Integer userId, @Valid PostBean form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("post/create_post")
                    .addObject("bindingResult", bindingResult).addObject("form", form);
        }

        Post post = new Post();
        post.setTopic(form.getTopic());
        post.setTitle(form.getTitle());
        post.setDescription(form.getDescription());
        post.setUserId(userId);
        postDAO.save(post);

        return new ModelAndView("redirect:/posts/my_posts");
    }

    // Editing Post
    @GetMapping("/posts/post/edit/{postId}")
    public ModelAndView displayEditPostPage(@PathVariable("postId") Integer postId) {
        Post post = postDAO.findById(postId);

        return new ModelAndView("post/create_post").addObject("post", post);
    }

    @PostMapping("/posts/edit/{postId}")
    public ModelAndView editPost(@PathVariable("postId") Integer postId, @Valid PostBean form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("post/create_post")
                    .addObject("bindingResult", bindingResult).addObject("form", form);
        }

        Post post = postDAO.findById(postId);
        post.setId(form.getId());
        post.setTopic(form.getTopic());
        post.setTitle(form.getTitle());
        post.setDescription(form.getDescription());
        post.setUserId(form.getUserId());
        postDAO.save(post);

        return new ModelAndView("redirect:/posts/post/" + postId);
    }

    // Deleting Post
    @PostMapping("/posts/delete/{postId}")
    public ModelAndView deletePost(@PathVariable("postId") Integer postId) {
        Post post = postDAO.findById(postId);
        postDAO.delete(post);

        return new ModelAndView("redirect:/posts");
    }
}
