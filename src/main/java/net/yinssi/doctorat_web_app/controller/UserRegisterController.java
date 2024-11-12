package net.yinssi.doctorat_web_app.controller;


import net.yinssi.doctorat_web_app.entity.User;
import net.yinssi.doctorat_web_app.service.UserService;
import net.yinssi.doctorat_web_app.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class UserRegisterController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, RedirectAttributes redirectAttributes) {
        try {
            userService.createUser(registrationDto);
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("errorMessage", "Registration failed: Already used email ");
        }

        return "redirect:/register?success";

    }

}
