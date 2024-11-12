// DisciplineService.java
package net.yinssi.doctorat_web_app.service;

import net.yinssi.doctorat_web_app.entity.Discipline;
import net.yinssi.doctorat_web_app.web.dto.DisciplineDto;

import java.util.List;

public interface DisciplineService {

    Discipline createDiscipline(DisciplineDto disciplineDto);
    List<Discipline> getDisciplines();
    Discipline getDisciplineById(long id);
    void deleteDisciplineById(long id);
}
