package net.yinssi.doctorat_web_app.repository;

import net.yinssi.doctorat_web_app.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
