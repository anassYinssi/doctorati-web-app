package net.yinssi.doctorat_web_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.yinssi.doctorat_web_app.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}