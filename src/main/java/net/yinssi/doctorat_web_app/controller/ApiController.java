package net.yinssi.doctorat_web_app.controller;


import net.yinssi.doctorat_web_app.entity.Application;
import net.yinssi.doctorat_web_app.entity.Document;
import net.yinssi.doctorat_web_app.entity.Speciality;
import net.yinssi.doctorat_web_app.service.ApplicationService;
import net.yinssi.doctorat_web_app.service.SpecialityService;
import net.yinssi.doctorat_web_app.web.dto.ApplicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private SpecialityService specialityService;
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/specialities")
    public List<Speciality> getSpecialitiesByDiscipline(@RequestParam Long disciplineId) {
        return specialityService.getSpecialitiesByDisciplineId(disciplineId);
    }

    @GetMapping("/applications/{id}/download-cv-pdf")
    public ResponseEntity<Resource> downloadCvPDF(@PathVariable Long id) throws IOException {
        Application application = applicationService.getApplicationById(id);
        Resource pdfResource = applicationService.getCvPDFResource(application);

        // Retrieve the document to get the filename
        Document document = application.getCv();
        String filename = (document != null && document.getFileName() != null) ? document.getFileName() : "document.pdf";

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(pdfResource);
    }


    @GetMapping("/applications/{id}/download-grades-pdf")
    public ResponseEntity<Resource> downloadGradesPDF(@PathVariable Long id) throws IOException {
        Application application = applicationService.getApplicationById(id);
        Resource pdfResource = applicationService.getGradesPDFResource(application);

        // Retrieve the document to get the filename
        Document document = application.getNotes();
        String filename = (document != null && document.getFileName() != null) ? document.getFileName() : "document.pdf";

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(pdfResource);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<ApplicationDto> approveApplication(@PathVariable Long id) {
        ApplicationDto updatedApplication = applicationService.updateApplicationStatus(id, "Approved");
        return ResponseEntity.ok(updatedApplication);
    }

    @PostMapping("/{id}/deny")
    public ResponseEntity<ApplicationDto> denyApplication(@PathVariable Long id) {
        ApplicationDto updatedApplication = applicationService.updateApplicationStatus(id, "Denied");
        return ResponseEntity.ok(updatedApplication);
    }

    @PostMapping("/applications/{id}/{action}")
    public ResponseEntity<Application> updateApplicationStatus(@PathVariable Long id, @PathVariable String action) {
        Application application = applicationService.getApplicationById(id);

        if (application == null) {
            return ResponseEntity.notFound().build();
        }

        if ("approve".equalsIgnoreCase(action)) {
            application.setStatus("Approved");
        } else if ("deny".equalsIgnoreCase(action)) {
            application.setStatus("Denied");
        } else if ("pend".equalsIgnoreCase(action)) {
            application.setStatus("Pending");
        }
        else {
            return ResponseEntity.badRequest().build();
        }

        applicationService.updateApplicationStatus(id,application.getStatus());
        return ResponseEntity.ok(application);
    }




}