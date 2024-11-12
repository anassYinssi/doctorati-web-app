// DisciplineRepository.java
package net.yinssi.doctorat_web_app.repository;

import net.yinssi.doctorat_web_app.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

}
