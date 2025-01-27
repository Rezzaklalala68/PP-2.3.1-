package ru.rezzaklalala68.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rezzaklalala68.model.User;
import ru.rezzaklalala68.service.UserService;

;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    @Autowired
    public UsersController( UserService userService ) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", new User());
        return "users";
    }

    @PostMapping("/add")
    public String addUser( @ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/add";
        }
        userService.add(user);
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/editUser";
        }
        userService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
         if(bindingResult.hasErrors()) {
             return "user/deleteUser";
         }
         userService.delete(user);

        return "redirect:/users";
    }
}
