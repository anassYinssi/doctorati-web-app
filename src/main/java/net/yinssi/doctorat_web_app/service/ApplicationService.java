package net.yinssi.doctorat_web_app.service;

import jakarta.transaction.Transactional;
import net.yinssi.doctorat_web_app.entity.Application;
import net.yinssi.doctorat_web_app.entity.Proposal;
import net.yinssi.doctorat_web_app.web.dto.ApplicationDto;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface ApplicationService {

    void createApplication(ApplicationDto application) throws IOException;
    List<Application> getStudentApplicationsById(long studentId);
    Application getApplicationByProposal(Proposal proposal);
    List<Application> getApplicationsByProposalIds(List<Proposal> proposal);
    List<Application> getAllApplications();
    Application getApplicationById(long applicationId);
    Resource getCvPDFResource(Application application);
    Resource getGradesPDFResource(Application application);

    ApplicationDto updateApplicationStatus(Long id, String status);


    void deleteApplicationById(long applicationId);


}
