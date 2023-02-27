package com.blog.controller;

import com.blog.model.User;
import com.blog.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/public/api/v1/posts")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "user/create-user";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        log.info("Received request to create a new user {}", user);
        if (bindingResult.hasErrors()) {
            log.error("Data not validated {}", bindingResult.getAllErrors());
            return "user/create-user";
        }
        service.save(user);
        return "redirect:/public/api/v1/posts";
    }

    @GetMapping("/{userId}/edit")
    public String updateUser(@PathVariable("userId") Long userId, Model model) {
        log.info("Received request to update a user {}", userId);
        model.addAttribute("user", service.getById(userId));
        return "user/update-user";
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") Long userId,
                             @ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        log.info("Update request received of user {} with new data {}", userId, user);
        if (bindingResult.hasErrors()) {
            log.error("Data not validated {}", bindingResult.getAllErrors());
            return "user/update-user";
        }
        service.update(userId, user);
        return "redirect:/public/api/v1/users/all";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        log.info("Received request to delete a post {}", userId);
        service.delete(userId);
        return "redirect:/public/api/v1/users/all";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        log.info("Received request to get all user's");
        model.addAttribute("users", service.getAllUsers());
        return "user/get-all-users";
    }
}