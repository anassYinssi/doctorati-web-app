// SpecialityServiceImpl.java
package net.yinssi.doctorat_web_app.service;

import net.yinssi.doctorat_web_app.entity.Speciality;
import net.yinssi.doctorat_web_app.repository.SpecialityRepository;
import net.yinssi.doctorat_web_app.web.dto.SpecialityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public void createSpeciality(SpecialityDto specialityDto) {
        Speciality speciality = new Speciality(specialityDto.getName(), specialityDto.getDiscipline());
        specialityRepository.save(speciality);
    }

    @Override
    public List<Speciality> getSpecialities() {
        return specialityRepository.findAll();
    }

    @Override
    public List<Speciality> getSpecialitiesByDisciplineId(Long disciplineId) {
        return specialityRepository.findByDisciplineId(disciplineId);
    }

    @Override
    public Speciality getSpecialityById(Long disciplineId) {
        return specialityRepository.findById(disciplineId).get();
    }

    @Override
    public void deleteSpecialityById(long id) {
        specialityRepository.deleteById(id);
    }


}
