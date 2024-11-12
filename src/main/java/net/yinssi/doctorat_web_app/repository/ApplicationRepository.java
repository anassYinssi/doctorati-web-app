package net.yinssi.doctorat_web_app.repository;


import net.yinssi.doctorat_web_app.entity.Application;
import net.yinssi.doctorat_web_app.entity.Proposal;
import net.yinssi.doctorat_web_app.entity.User;
import net.yinssi.doctorat_web_app.web.dto.ApplicationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudentId(long studentId);
    List<Application> findAllByProposalId(long proposalId);
    Application findByProposalId(long proposalId);
    Application findByProposal(Proposal proposal);

    @Modifying
    @Query("UPDATE Application a SET a.status = :status WHERE a.id = :id")
    int updateApplicationStatus(@Param("id") Long id, @Param("status") String status);

}
