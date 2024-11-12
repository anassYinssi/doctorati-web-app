package net.yinssi.doctorat_web_app.controller;

import net.yinssi.doctorat_web_app.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequestMapping("/admin/specialities")
public class SpecialityController {
    @Autowired
    private SpecialityService specialityService;

    @GetMapping("/delete/{id}")
    public String deleteSpeciality(@PathVariable Long id) {
        specialityService.deleteSpecialityById(id);
        return "redirect:/admin/specialities"; // Ensure this URL is correct
    }

}
