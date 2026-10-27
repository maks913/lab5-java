package ua.edu.nuos.lab5java.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.nuos.lab5java.data.User;
import ua.edu.nuos.lab5java.service.UserService;
import ua.edu.nuos.lab5java.service.VacancyService;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
class UserController {

    private final VacancyService vacancyService;
    private UserService userService;

    @GetMapping
    String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users/users";
    }

    @GetMapping("/{id}")
    String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "users/update_form";
    }

    @GetMapping("/detail/{id}")
    String viewDetail(@PathVariable Integer id, Model model) {
        var user = userService.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        model.addAttribute("user", user);
        model.addAttribute("vacancies", vacancyService.getVacancies());
        return "users/user_detail";
    }

    @GetMapping("/new")
    String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/create_form";
    }

    @PostMapping("/save")
    String createUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    String deleteUser(@PathVariable Integer id) {
        if (id == null) throw new RuntimeException("Id is null");
        userService.deleteById(id);
        return "redirect:/users";
    }

    @PostMapping("/save-list")
    @ResponseBody
    public ResponseEntity<Object> saveVacancy(
            @RequestParam Integer userId,
            @RequestParam Integer vacancyId) {
        userService.saveVacancy(userId, vacancyId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
