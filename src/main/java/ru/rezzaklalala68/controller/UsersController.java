package ru.rezzaklalala68.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rezzaklalala68.model.User;
import ru.rezzaklalala68.service.UserService;



@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    @Autowired
    public UsersController( UserService userService ) {
        this.userService = userService;
    }
    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getUsers());

        return "user-list";
    }

    @GetMapping("/add-user")
    public String showAddUser ( Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }
    @PostMapping("/add-user")
    public String addUser( @ModelAttribute("user") @Validated User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-user";
        }
        userService.add(user);
        return "redirect:/users";
    }
    @GetMapping("/edit-user")
    public String showEditUser (@RequestParam("id") Long id, Model model) {
        User userById = userService.findUserById(id);
        if (userById != null) {
            model.addAttribute("user", userById);
            return "edit-user";
        } else {
            return "redirect:/users";
        }
    }
    @PostMapping("/edit-user")
    public String updateUser(@ModelAttribute("user") @Validated User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-user";
        }
        userService.update(user);
        return "redirect:/users";
    }
    @GetMapping("/delete-user")
    public String showDeleteUser(@RequestParam("id") Long id, Model model) {
        User userById = userService.findUserById(id);
        if (userById != null) {
            model.addAttribute("user", userById);
            return "delete-user";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam("id") Long id) {

         userService.delete(id);

        return "redirect:/users";
    }
}
