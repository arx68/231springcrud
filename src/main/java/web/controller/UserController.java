package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    void init() {
        for (int i = 0; i < 10; i++) {
            userService.saveUser(new User("Ivan", "Ivanov", i));
        }
    }

    @GetMapping("/")
    public String indexUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/user/{id}")
    public String usersId(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @PatchMapping(value = "/user/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String showNewUserPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "createuser";
    }

    @PostMapping("/newuser")
    public String createUser(@ModelAttribute("user") User user, ModelMap model) {
        if (user != null) {
            userService.saveUser(user);
        }
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deletUser(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/";
    }
}