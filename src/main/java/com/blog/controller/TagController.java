package com.blog.controller;

import com.blog.model.Tag;
import com.blog.service.TagService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("public/api/v1/tags")
public class TagController {

    private static final Logger log = LoggerFactory.getLogger(TagController.class);

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/{postId}")
    public String createComment(@PathVariable("postId") Long postId,
                                @ModelAttribute("tag") @Valid Tag tag, BindingResult bindingResult) {
        log.info("Received request to create a new tag {}", tag);
        if (bindingResult.hasErrors()) {
            log.error("Data not validated {}", bindingResult.getAllErrors());
            return "redirect:/public/api/v1/posts/{postId}";
        }
        tagService.save(tag, postId);
        return "redirect:/public/api/v1/posts/{postId}";
    }

    @GetMapping("/{tagId}/edit")
    public String updateComment(@PathVariable("tagId") Long tagId, Model model) {
        Tag tag = tagService.getById(tagId);
        log.info("Received request to update a tag {}", tag);
        model.addAttribute("tag", tag);
        return "tag/update-tag";
    }

    @PatchMapping("/{tagId}")
    public String updateComment(@PathVariable("tagId") Long tagId,
                                @ModelAttribute("comment") @Valid Tag tag, BindingResult bindingResult) {
        log.info("Update request received of comment {} with new data {}", tagId, tag);
        if (bindingResult.hasErrors()) {
            log.error("Data not validated {}", bindingResult.getAllErrors());
            return "tag/update-tag";
        }
        tagService.update(tagId, tag);
        return "redirect:/public/api/v1/posts";
    }

    @DeleteMapping("/{postId}/{tagId}")
    public String deleteComment(@PathVariable("postId") Long postId, @PathVariable("tagId") Long tagId) {
        log.info("Received request to delete a comment {}", tagId);
        tagService.delete(tagId);
        return "redirect:/public/api/v1/posts/{postId}";
    }
}