package net.yinssi.doctorat_web_app.service;

import net.yinssi.doctorat_web_app.entity.Proposal;
import net.yinssi.doctorat_web_app.web.dto.ProposalDto;

import java.util.List;

public interface ProposalService {

    void createProposal(ProposalDto proposalDto);
    List<Proposal> getProposalsByProfessorId(Long professorId);
    List<Proposal> getAllProposals();
    Proposal getProposalById(Long proposalId);
    void deleteProposalById(Long proposalId);
    void updateProposal(long id, ProposalDto proposalDto);


}
