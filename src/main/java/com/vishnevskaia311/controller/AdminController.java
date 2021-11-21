package com.vishnevskaia311.controller;

import com.vishnevskaia311.model.User;
import com.vishnevskaia311.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import com.vishnevskaia311.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(Model model, Principal principal) {
        List<User> users = userService.index();
        String username = principal.getName();
        User user = userService.getUserByName(username);
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        model.addAttribute("allroles", roleService.getRoleSet());
        return "admin";
    }

//    @GetMapping("/new")
//    public String addUser(Model model) {
//        model.addAttribute("user", new User());
//        return "admin/new";
//    }
//
//    @PostMapping()
//    public String create(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return "redirect:/admin";
//    }

    @GetMapping("/admin")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.show(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
