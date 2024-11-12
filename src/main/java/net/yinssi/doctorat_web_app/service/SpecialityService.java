// SpecialityService.java
package net.yinssi.doctorat_web_app.service;

import net.yinssi.doctorat_web_app.entity.Speciality;
import net.yinssi.doctorat_web_app.web.dto.SpecialityDto;

import java.util.List;

public interface SpecialityService {
    void createSpeciality(SpecialityDto specialityDto);

    List<Speciality> getSpecialities();

    List<Speciality> getSpecialitiesByDisciplineId(Long disciplineId);

    Speciality getSpecialityById(Long disciplineId);

    void deleteSpecialityById(long id);
}
