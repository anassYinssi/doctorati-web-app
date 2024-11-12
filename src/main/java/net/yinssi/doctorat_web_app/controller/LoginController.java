package net.yinssi.doctorat_web_app.controller;


import net.yinssi.doctorat_web_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService; // Assume this service handles user retrieval and authentication

    @GetMapping
    public String login() {
        return "login";
}

    @PostMapping
    public RedirectView login(@RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes) {
        try {
            UserDetails userDetails = userService.loadUserByUsername(email);
            redirectAttributes.addFlashAttribute("successMessage", "Login successfuly!");

            if (userDetails != null && userService.checkPassword(userDetails, password)) {
                // Assuming userService provides a way to get the user's role
            }

        } catch (Exception e) {
            redirectAttributes.addAttribute("errorMessage", "Invalid email or password! ");
        }
        return new RedirectView("/login?error");
    }
}
