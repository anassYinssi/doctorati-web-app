package net.yinssi.doctorat_web_app.controller;


import net.yinssi.doctorat_web_app.entity.*;
import net.yinssi.doctorat_web_app.repository.UserRepository;
import net.yinssi.doctorat_web_app.service.*;
import net.yinssi.doctorat_web_app.web.dto.ApplicationDto;
import net.yinssi.doctorat_web_app.web.dto.DisciplineDto;
import net.yinssi.doctorat_web_app.web.dto.ProposalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ApplicationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private DisciplineService disciplineService;

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private DocumentService documentService;


    @PostMapping("/createApplication")
    public String createApplication(@ModelAttribute("application") ApplicationDto applicationDto, Model model) throws IOException {
        User currentStudent = userService.getCurrentUser();
        applicationDto.setStudent(currentStudent);
        applicationDto.setApplicationDate(Date.valueOf(LocalDate.now()));
        applicationDto.setApplicationTime(Time.valueOf(LocalTime.now()));

        applicationService.createApplication(applicationDto);

        return "redirect:/api/createApplication";
    }

    @GetMapping("/student/newApplication")
    public String newApplication_student(Model model) {
        List<Proposal> proposals = proposalService.getAllProposals();
        List<ProposalDto> proposalDtos = proposals.stream().map(proposal -> {
            ProposalDto dto = new ProposalDto();
            dto.setId(proposal.getId());
            dto.setTopic(proposal.getTopic());
            dto.setSkills(proposal.getSkills());
            dto.setObjectives(proposal.getObjectives());
            dto.setDescription(proposal.getDescription());
            dto.setProposalDate(proposal.getProposalDate());
            dto.setProposalTime(proposal.getProposalTime());

            // Fetch names using service layers
            Discipline discipline = disciplineService.getDisciplineById(proposal.getDiscipline().getId());
            Speciality speciality = specialityService.getSpecialityById(proposal.getSpeciality().getId());
            User professor = userService.getUserById(proposal.getProfessor().getId());

            dto.setDisciplineName(discipline.getName());
            dto.setSpecialityName(speciality.getName());
            dto.setProfessorFullName("Pr. "+professor.getFirstname()+" "+professor.getLastname());

            return dto;
        }).collect(Collectors.toList());
        model.addAttribute("proposals", proposalDtos);
        return "/student/newApplication";
    }

    @GetMapping("/student/myApplications")
    public String listApplications(Model model, Principal principal) {
        User currentStudent = userService.findByEmail(principal.getName());

        List<Application> applications = applicationService.getStudentApplicationsById(currentStudent.getId());
        model.addAttribute("student_applications", applications);

        return "/student/myApplications";
    }

    @GetMapping("/student/apply/{proposalId}")
    public String showApplyPage(@PathVariable Long proposalId, Model model) {
        // Fetch proposal details based on the ID
        Proposal proposal = proposalService.getProposalById(proposalId);
        ApplicationDto applicationDto =new ApplicationDto();

        // Add the proposal details to the model
        model.addAttribute("proposal", proposal);
        model.addAttribute("application", applicationDto);

        return "/student/applyProposal"; // This corresponds to the 'applyProposal.html' page
    }

    @PostMapping("/submitApplication")
    public String submitApplication(@ModelAttribute("application") ApplicationDto applicationDto, @RequestParam("proposalId") long proposalId ,Model model) throws IOException {
        User currentStudent = userService.getCurrentUser();
        applicationDto.setStudent(currentStudent);
        applicationDto.setApplicationDate(Date.valueOf(LocalDate.now()));
        applicationDto.setApplicationTime(Time.valueOf(LocalTime.now()));
        applicationDto.setStatus("Pending");

        Proposal proposal = proposalService.getProposalById(proposalId);
        applicationDto.setProposal(proposal);
        proposal.setNumberOfApplications(+1);

        applicationService.createApplication(applicationDto);

        return "redirect:/student/myApplications";
    }

    @GetMapping("/professor/manageApplications")
    public String manageApplications_professor(Model model) {
        // Initialize a list to store application IDs
        List<Long> applicationIds = new ArrayList<>();  // Use List<Long> instead of List<long>

        // Get the current professor
        User currentProfessor = userService.getCurrentUser();

        // Get proposals for the current professor
        List<Proposal> proposals = proposalService.getProposalsByProfessorId(currentProfessor.getId());


        List<Application> applications = applicationService.getApplicationsByProposalIds(proposals);


        model.addAttribute("applications", applications);

        return "/professor/manageApplications";  // Ensure this returns the correct view
    }

    @GetMapping("/student/manageApplications/deleteApplication/{id}")
    public String deleteProposal(@PathVariable Long id) {
        applicationService.deleteApplicationById(id);
        return "redirect:/student/myApplications"; // Ensure this URL is correct
    }

    @GetMapping("/admin/currentApplications")
    public String currentApplications_admin(Model model) {
        List<Application> applications =applicationService.getAllApplications();
        model.addAttribute("Applications", applications);
        return "/admin/currentApplications";
    }












}
