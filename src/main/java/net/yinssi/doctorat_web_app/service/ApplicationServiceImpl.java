package net.yinssi.doctorat_web_app.service;

import net.yinssi.doctorat_web_app.entity.Application;
import net.yinssi.doctorat_web_app.entity.Document;
import net.yinssi.doctorat_web_app.entity.Proposal;
import net.yinssi.doctorat_web_app.repository.ApplicationRepository;
import net.yinssi.doctorat_web_app.web.dto.ApplicationDto;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {


    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private DocumentService documentService;


    @Override
    public void createApplication(ApplicationDto applicationDto) throws IOException {
        Application app = new Application(applicationDto.getNum_inscription(),applicationDto.getMotivation(),
                applicationDto.getCursus(), applicationDto.getStatus(), applicationDto.getStudent(),
                applicationDto.getProposal(), applicationDto.getApplicationDate(), applicationDto.getApplicationTime());

        app.setCv(documentService.uploadPDF(applicationDto.getCv().getOriginalFilename(), applicationDto.getCv().getInputStream()));
        app.setNotes(documentService.uploadPDF(applicationDto.getNotes().getOriginalFilename(), applicationDto.getNotes().getInputStream()));

        applicationRepository.save(app);
    }

    @Override
    public List<Application> getStudentApplicationsById(long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }

    @Override
    public Application getApplicationByProposal(Proposal proposal) {
        return applicationRepository.findByProposal(proposal);
    }

    @Override
    public List<Application> getApplicationsByProposalIds(List<Proposal> proposals) {
        List<Application> applications = new ArrayList<>();

        // Iterate over each proposal and retrieve the corresponding application
        for (Proposal proposal : proposals) {
            Application application = getApplicationByProposal(proposal);
            if (application != null) {  // Check if an application exists for the proposal
                applications.add(application);
            }
        }

        return applications;
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Application getApplicationById(long applicationId) {
        return applicationRepository.findById(applicationId).get();
    }

    @Override
    public Resource getCvPDFResource(Application application) {
        // Retrieve the associated Document
        Document document = application.getCv();

        if (document == null || document.getData() == null) {
            throw new RuntimeException("CV document not found for the application");
        }

        // Return the document data as a ByteArrayResource
        return new ByteArrayResource(document.getData());
    }

    @Override
    public Resource getGradesPDFResource(Application application) {
        // Retrieve the associated Document
        Document document = application.getNotes();

        if (document == null || document.getData() == null) {
            throw new RuntimeException("Grades document not found for the application");
        }

        // Return the document data as a ByteArrayResource
        return new ByteArrayResource(document.getData());
    }

    @Transactional
    public ApplicationDto updateApplicationStatus(Long id, String status) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));

        application.setStatus(status);
        Application updatedApplication = applicationRepository.save(application);

        return convertToDTO(updatedApplication);
    }

    // Helper method to convert Application to ApplicationDTO
    private ApplicationDto convertToDTO(Application application) {
        // Implement the conversion logic here
        // This is a simplified example, adjust according to your actual DTO structure
        ApplicationDto dto = new ApplicationDto();
        dto.setId(application.getId());
        dto.setStatus(application.getStatus());
        // Set other fields...
        return dto;
    }


    @Override
    public void deleteApplicationById(long applicationId) {
        applicationRepository.deleteById(applicationId);
    }
}
