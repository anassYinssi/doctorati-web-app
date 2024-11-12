package net.yinssi.doctorat_web_app.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import net.yinssi.doctorat_web_app.entity.Proposal;
import net.yinssi.doctorat_web_app.repository.ProposalRepository;
import net.yinssi.doctorat_web_app.web.dto.ProposalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    ProposalRepository proposalRepository;

    @Override
    public void createProposal(ProposalDto proposalDto) {
        Proposal proposal = new Proposal(proposalDto.getTopic(), proposalDto.getDiscipline(),
                proposalDto.getSpeciality(), proposalDto.getSkills(), proposalDto.getObjectives(),
                proposalDto.getDescription(),proposalDto.getProfessor(), proposalDto.getProposalDate(),
                proposalDto.getProposalTime());

        proposalRepository.save(proposal);
    }

    @Override
    public List<Proposal> getProposalsByProfessorId(Long professorId) {
        return proposalRepository.findByProfessorId(professorId);
    }

    @Override
    public List<Proposal> getAllProposals() {

        return proposalRepository.findAll();
    }

    @Override
    public Proposal getProposalById(Long proposalId) {
        return proposalRepository.findById(proposalId).get();
    }

    @Override
    public void deleteProposalById(Long proposalId) {

        proposalRepository.deleteById(proposalId);
    }

    @Override
    public void updateProposal(long id, ProposalDto proposalDto) {
        Proposal proposal = proposalRepository.findById(id).get();
        proposal.setTopic(proposalDto.getTopic());
        proposal.setDiscipline(proposalDto.getDiscipline());
        proposal.setSpeciality(proposalDto.getSpeciality());
        proposal.setSkills(proposalDto.getSkills());
        proposal.setObjectives(proposalDto.getObjectives());
        proposal.setDescription(proposalDto.getDescription());
        proposal.setProposalDate(proposalDto.getProposalDate());
        proposal.setProposalTime(proposalDto.getProposalTime());
        proposal.setEdited(proposalDto.isEdited());
        proposal.setNumberOfApplications(proposalDto.getNumberOfApplications());
        proposalRepository.save(proposal);
    }
}
