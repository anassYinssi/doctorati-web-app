// DisciplineServiceImpl.java
package net.yinssi.doctorat_web_app.service;

import net.yinssi.doctorat_web_app.entity.Discipline;
import net.yinssi.doctorat_web_app.repository.DisciplineRepository;
import net.yinssi.doctorat_web_app.web.dto.DisciplineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineServiceImpl implements DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Override
    public Discipline createDiscipline(DisciplineDto disciplineDto) {
        Discipline discipline = new Discipline(disciplineDto.getName(), disciplineDto.getSpecialities());
        return disciplineRepository.save(discipline);
    }

    @Override
    public List<Discipline> getDisciplines() {
        return disciplineRepository.findAll();
    }

    @Override
    public Discipline getDisciplineById(long id) {
        return disciplineRepository.findById(id).get();
    }

    @Override
    public void deleteDisciplineById(long id) {
        disciplineRepository.deleteById(id);
    }

}
