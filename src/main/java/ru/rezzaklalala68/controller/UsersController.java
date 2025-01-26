package ru.rezzaklalala68.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rezzaklalala68.model.User;
import ru.rezzaklalala68.service.UserService;

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
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/userList";
    }
    @GetMapping("/add")
    public String showAddUserForm(User user) {
        return "user/addUser";
    }
    @PostMapping("/add")
    @ResponseBody
    public String addUser( @ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/addUser";
        }
        userService.add(user);
        return "redirect:/users";
    }
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "redirect:/users";
        }
        else {return "editUser";}

    }
    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/editUser";
        }
        userService.update(user);
        return "redirect:/users";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        Optional<User> userById = userService.findUserById(id);
        if(userById.isPresent()) {
            model.addAttribute("user", userById.get());
            return "user/deleteUser";
        }
        else {
            return "redirect:/users";
        }
    }
    @PostMapping("/delete/{id}")
    public String deleteUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
         if(bindingResult.hasErrors()) {
             return "user/deleteUser";
         }

        return "redirect:/users";
    }
}
