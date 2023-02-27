package com.blog.controller;

import com.blog.model.Comment;
import com.blog.service.CommentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("public/api/v1/comments")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public String createComment(@PathVariable("postId") Long postId,
                                @ModelAttribute("comment") @Valid Comment comment, BindingResult bindingResult) {
        log.info("Received request to create a new comment {}", comment);
        if (bindingResult.hasErrors()) {
            log.error("Data not validated {}", bindingResult.getAllErrors());
            return "redirect:/public/api/v1/comments/{postId}";
        }
        commentService.save(comment, postId);
        return "redirect:/public/api/v1/posts/{postId}";
    }

    @GetMapping("/{commentId}/edit")
    public String updateComment(@PathVariable("commentId") Long commentId, Model model) {
        Comment comment = commentService.getById(commentId);
        log.info("Received request to update a comment {}", comment);
        model.addAttribute("comment", comment);
        return "comment/update-comment";
    }

    @PatchMapping("/{commentId}")
    public String updateComment(@PathVariable("commentId") Long commentId, Comment comment, BindingResult bindingResult) {
        log.info("Update request received of comment {} with new data {}", commentId, comment);
        if (bindingResult.hasErrors()) {
            log.error("Data not validated {}", bindingResult.getAllErrors());
            return "comment/update-comment";
        }
        commentService.update(commentId, comment);
        return "redirect:/public/api/v1/posts";
    }

    @DeleteMapping("/{postId}/{commentId}")
    public String deleteComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        log.info("Received request to delete a comment {}", commentId);
        commentService.delete(commentId);
        return "redirect:/public/api/v1/posts/{postId}";
    }
}