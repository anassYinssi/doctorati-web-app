package net.yinssi.doctorat_web_app.controller;


import net.yinssi.doctorat_web_app.entity.Discipline;
import net.yinssi.doctorat_web_app.entity.Speciality;
import net.yinssi.doctorat_web_app.service.DisciplineService;
import net.yinssi.doctorat_web_app.service.SpecialityService;
import net.yinssi.doctorat_web_app.web.dto.DisciplineDto;
import net.yinssi.doctorat_web_app.web.dto.SpecialityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private DisciplineService disciplineService;
    @Autowired
    private SpecialityService specialityService;

    @PostMapping("/addDiscipline")
    public String addDiscipline(@ModelAttribute("discipline")DisciplineDto disciplineDto) {
        disciplineDto.setSpecialities(new HashSet<Speciality>());
        disciplineService.createDiscipline(disciplineDto);
        return "redirect:/admin/specialities";
    }

    @PostMapping("/addSpeciality")
    public String addDiscipline(@ModelAttribute("speciality") SpecialityDto specialityDto) {
        specialityService.createSpeciality(specialityDto);
        return "redirect:/admin/specialities";
    }

    @GetMapping("admin/specialities")
    public String getDisciplines(Model model) {
        List<Discipline> disciplines =disciplineService.getDisciplines();
        List<Speciality> spec =specialityService.getSpecialities();
        model.addAttribute("disciplines", disciplines);
        model.addAttribute("specialities", spec);
        return "admin/specialities";
    }


}
