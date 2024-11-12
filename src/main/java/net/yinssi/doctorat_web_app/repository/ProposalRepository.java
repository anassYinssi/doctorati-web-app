package net.yinssi.doctorat_web_app.repository;


import net.yinssi.doctorat_web_app.entity.Proposal;
import net.yinssi.doctorat_web_app.web.dto.ProposalDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.yinssi.doctorat_web_app.entity.User;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    List<Proposal> findByProfessorId(Long professorId);
    List<Proposal> findBySpeciality_Id(Long specialityId);


}
