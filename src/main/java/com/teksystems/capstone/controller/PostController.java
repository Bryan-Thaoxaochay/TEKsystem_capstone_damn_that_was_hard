package com.teksystems.capstone.controller;

import com.teksystems.capstone.bean.PostBean;
import com.teksystems.capstone.database.dao.CommentDAO;
import com.teksystems.capstone.database.dao.PostDAO;
import com.teksystems.capstone.database.dao.UserDAO;
import com.teksystems.capstone.database.entity.Comment;
import com.teksystems.capstone.database.entity.Post;
import com.teksystems.capstone.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.ArrayList;
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

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/posts/my_posts")
    public ModelAndView getUserPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userDAO.findByEmail(authentication.getName());

        List<Post> userPosts = postDAO.findByUserId(currentUser.getUserId());

        return new ModelAndView("post/posts").addObject("posts", userPosts);
    }

    @GetMapping("/posts/post/{postId}")
    public ModelAndView getPostAndComments(@PathVariable("postId") Integer postId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userDAO.findByEmail(authentication.getName());

        Post post = postDAO.findById(postId);
        List<Comment> comments = commentDAO.getAllByBlogpostIdOrderByUpdateDateDesc(postId);
        User postUser = userDAO.findByUserId(post.getUserId());
        List<String> commentUsers = new ArrayList<>();

        comments.forEach(comment -> commentUsers.add(userDAO.findByUserId(comment.getUserId()).getUsername()));

        return new ModelAndView("post/post")
            .addObject("post", post).addObject("comments", comments)
            .addObject("currentUser", currentUser).addObject("postUser", postUser)
            .addObject("commentUsers", commentUsers).addObject("numOfComments", comments.stream().count());
    }

    // Creating Post
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/posts/create_post")
    public ModelAndView displayCreatePostPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userDAO.findByEmail(authentication.getName());

        return new ModelAndView("post/create_post").addObject("currentUser", currentUser);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/posts/create/{userId}")
    public ModelAndView createPost(@PathVariable("userId") Integer userId, @Valid PostBean form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("post/create_post").addObject("bindingResult", bindingResult)
                                                                .addObject("form", form);
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
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/posts/post/edit/{postId}")
    public ModelAndView displayEditPostPage(@PathVariable("postId") Integer postId) {
        Post post = postDAO.findById(postId);

        return new ModelAndView("post/create_post").addObject("post", post);
    }

    @PreAuthorize("hasAuthority('USER')")
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
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/posts/delete/{postId}")
    public ModelAndView deletePost(@PathVariable("postId") Integer postId) {
        Post post = postDAO.findById(postId);
        postDAO.delete(post);

        return new ModelAndView("redirect:/posts");
    }
}
