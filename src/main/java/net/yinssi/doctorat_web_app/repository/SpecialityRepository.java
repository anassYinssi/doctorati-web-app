// SpecialityRepository.java
package net.yinssi.doctorat_web_app.repository;

import net.yinssi.doctorat_web_app.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    List<Speciality> findByDisciplineId(Long disciplineId);
}
