package ru.rezzaklalala68.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rezzaklalala68.model.User;
import ru.rezzaklalala68.service.UserService;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    @Autowired
    public UsersController( UserService userService ) {
        this.userService = userService;
    }
    @GetMapping("/UserList")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getUsers());

        return "UserList";
    }

    @GetMapping("/AddUser")
    public String showAddUser ( Model model) {
        model.addAttribute("user", new User());
        return "AddUser";
    }
    @PostMapping("/AddUser")
    public String addUser( @ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "AddUser";
        }
        userService.add(user);
        return "redirect:/users";
    }
    @GetMapping("/EditUser")
    public String showEditUser (@RequestParam("id") long id, Model model) {
        Optional<User> userById = userService.findUserById(id);
        if (userById.isPresent()) {
            model.addAttribute("user", userById.get());
            return "EditUser";
        } else {
            return "redirect:/users";
        }
    }
    @PostMapping("/EditUser")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editUser";
        }
        userService.update(user);
        return "redirect:/users";
    }
    @GetMapping("/DeleteUser")
    public String showDeleteUser(@RequestParam("id") long id, Model model) {
        Optional<User> userById = userService.findUserById(id);
        if (userById.isPresent()) {
            model.addAttribute("user", userById.get());
            return "DeleteUser";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/DeleteUser")
    public String deleteUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
         if(bindingResult.hasErrors()) {
             return "DeleteUser";
         }
         userService.delete(user);

        return "redirect:/users";
    }
}
