package net.yinssi.doctorat_web_app.controller;



import net.yinssi.doctorat_web_app.entity.Discipline;
import net.yinssi.doctorat_web_app.entity.Proposal;
import net.yinssi.doctorat_web_app.entity.Speciality;
import net.yinssi.doctorat_web_app.entity.User;
import net.yinssi.doctorat_web_app.repository.UserRepository;
import net.yinssi.doctorat_web_app.service.DisciplineService;
import net.yinssi.doctorat_web_app.service.ProposalService;
import net.yinssi.doctorat_web_app.service.SpecialityService;
import net.yinssi.doctorat_web_app.service.UserService;
import net.yinssi.doctorat_web_app.web.dto.ProposalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ui.Model;


@Controller
public class ProposalController {

    @Autowired
    private ProposalService proposalService;
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private UserService userService;
    @Autowired
    private DisciplineService disciplineService;
    @Autowired
    private SpecialityService specialityService;


    @GetMapping("/professor/createProposal")
    public String createProposal(Model model) {
        model.addAttribute("proposal", new ProposalDto());
        model.addAttribute("specialities", specialityService.getSpecialities());
        model.addAttribute("disciplines", disciplineService.getDisciplines());
        return "/professor/createProposal";
    }

    @PostMapping("/professor/createProposal")
    public String submitProposal(@ModelAttribute("proposal") ProposalDto proposalDto, BindingResult bindingResult, Model model) {

        proposalDto.setProfessor(userService.getCurrentUser());

        proposalDto.setProposalDate(Date.valueOf(LocalDate.now()));
        proposalDto.setProposalTime(Time.valueOf(LocalTime.now()));

        proposalService.createProposal(proposalDto);

        return "redirect:/professor/home";
    }

    @GetMapping("/professor/manageProposals")
    public String showProfessorProposals(Model model, Principal principal) {
        // Get the currently logged-in user's email
        String email = principal.getName();

        // Find the user by email (assuming you have a method for this in your UserService)
        User currentProfessor = userService.findByEmail(email);

        // Fetch proposals for this professor
        List<Proposal> proposals = proposalService.getProposalsByProfessorId(currentProfessor.getId());

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

            dto.setDisciplineName(discipline.getName());
            dto.setSpecialityName(speciality.getName());
            dto.setProfessorFullName("Pr. "+currentProfessor.getFirstname()+" "+currentProfessor.getLastname()); // Assuming you have a method to get the full name

            return dto;
        }).collect(Collectors.toList());

        ProposalDto updatedProposalDto =new ProposalDto();

        // Add proposals to the model
        model.addAttribute("disciplines", disciplineService.getDisciplines());
        //model.addAttribute("specialities", specialityService.getSpecialities());
        model.addAttribute("proposals", proposalDtos);
        model.addAttribute("updatedProposal", updatedProposalDto);

        return "/professor/manageProposals"; // Adjust to your actual view name
    }

    @PostMapping("/updateProposal")
    public String UpdateProposal(@ModelAttribute("updatedProposal") ProposalDto proposalDto){
        //proposalDto.setProfessor(userService.getCurrentUser());
        proposalDto.setProposalDate(Date.valueOf(LocalDate.now()));
        proposalDto.setProposalTime(Time.valueOf(LocalTime.now()));
        proposalDto.setEdited(true);
        proposalService.updateProposal(proposalDto.getId(),proposalDto);
        return "redirect:/professor/manageProposals";
    }

    @GetMapping("/professor/manageProposals/deleteProposal/{id}")
    public String deleteProposal(@PathVariable Long id) {
        proposalService.deleteProposalById(id);
        return "redirect:/professor/manageProposals"; // Ensure this URL is correct
    }

    @GetMapping("/admin/currentProposals")
    public String currentProposals_admin(Model model) {
        List<Proposal> proposals = proposalService.getAllProposals();
        model.addAttribute("Proposals", proposals);
        return "/admin/currentProposals";
    }

}
