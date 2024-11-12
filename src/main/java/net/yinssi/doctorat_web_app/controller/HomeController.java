package net.yinssi.doctorat_web_app.controller;

import jakarta.servlet.http.HttpServletRequest;
import net.yinssi.doctorat_web_app.entity.Application;
import net.yinssi.doctorat_web_app.entity.Proposal;
import net.yinssi.doctorat_web_app.entity.Speciality;
import net.yinssi.doctorat_web_app.entity.User;
import net.yinssi.doctorat_web_app.service.*;
import net.yinssi.doctorat_web_app.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ProposalService proposalService;


    @GetMapping("/admin/home")
    public String home_admin(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            model.addAttribute("firstname", userDetails.getFirstName()); // Correctly retrieve first name
        } else {
            model.addAttribute("firstname", "Guest");
        }
        return "/admin/home";
    }


    @GetMapping("/professor/home")
    public String home_professor(Model model) {
        User currentProfessor = userService.getCurrentUser();
        if (currentProfessor!=null) {
            model.addAttribute("firstname", currentProfessor.getFirstname()); // Correctly retrieve first name
        } else {
            model.addAttribute("firstname", "Guest");
        }

        List<Proposal> proposals = proposalService.getProposalsByProfessorId(currentProfessor.getId());

        List<Application> applications = applicationService.getApplicationsByProposalIds(proposals);

        List<Application> approvedApplications = new ArrayList<>();
        List<Application> deniedApplications = new ArrayList<>();

        for (int i = 0; i < applications.size(); i++) {
            Application application = applications.get(i);
            if(Objects.equals(application.getStatus(), "Approved")){
                approvedApplications.add(application);
            }else if(Objects.equals(application.getStatus(), "Denied")){
                deniedApplications.add(application);
            }
        }


        model.addAttribute("TotalApplications", applications.size());
        model.addAttribute("ActiveProposals", proposals.size());
        model.addAttribute("ApprovedApplications", approvedApplications);
        model.addAttribute("DeniedApplications", deniedApplications);

        return "/professor/home";
    }


    @GetMapping("/student/home")
    public String home_student(Model model) {
        User currentStudent = userService.getCurrentUser();
        if (currentStudent!=null) {
            model.addAttribute("firstname", currentStudent.getFirstname()); // Correctly retrieve first name
        } else {
            model.addAttribute("firstname", "Guest");
        }

        List<Application> applications = applicationService.getStudentApplicationsById(currentStudent.getId());

        List<Proposal> proposals = proposalService.getAllProposals();

        model.addAttribute("TotalStudentApplications", applications.size());
        model.addAttribute("ExistedOffer", proposals.size());




        return "/student/home";
    }


    @GetMapping("/profile")
    public String profile_student(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("userData", user);
        return "/profile";
    }

    @PostMapping("/updateProfile")
    public String update_profile(@ModelAttribute("userData") UserRegistrationDto userRegistrationDto, BindingResult result, Model model, HttpServletRequest request){

        if (result.hasErrors()) {
            return "/profile";
        }

        User currentUser = userService.getCurrentUser();
        boolean emailChanged = !currentUser.getEmail().equals(userRegistrationDto.getEmail());
        boolean passwordChanged = userRegistrationDto.getPassword() != null && !userRegistrationDto.getPassword().isEmpty();

        userService.updateUser(userRegistrationDto);

        if (emailChanged || passwordChanged) {
            // Invalidate the current session to force re-authentication
            request.getSession().invalidate();
            // Redirect to the login page
            return "redirect:/login?updateSuccess";
        }


        model.addAttribute("successMessage", "Profile updated successfully!");


        return "/profile";
    }






}
